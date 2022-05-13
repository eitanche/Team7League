package dataBase;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBInitiator {
    private final String pathToResources = "src/main/resources";
    private MongoDatabase database;

    public DBInitiator() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("Database");
    }

    public void addAllJsonsToDB() throws IOException {
         for (String jsonFileName: listFilesUsingJavaIO(pathToResources)) {
             loadDBFromJsonFile(jsonFileName);
         }
    }

    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public void loadDBFromJsonFile(String jsonFileName) throws IOException {
        String collectionName = jsonFileName.substring(0, jsonFileName.indexOf('.'));
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteMany(new Document());
        //drop previous import
        collection.drop();

        //Bulk Approach:
        int count = 0;
        int batch = 100;
        List<InsertOneModel<Document>> docs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToResources+"/"+jsonFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    docs.add(new InsertOneModel<>(Document.parse(line)));
                }
                catch (Exception e) {
                    System.out.println(collectionName);
                    throw e;
                }
                count++;
                if (count == batch) {
                    collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
                    docs.clear();
                    count = 0;
                }
            }
        }

        if (count > 0) {
            BulkWriteResult bulkWriteResult=  collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
            System.out.println("Inserted" + bulkWriteResult);
        }
    }

    public void printDBDocuments(String collectionName) {
        for (Document d: database.getCollection(collectionName).find())
            System.out.println(d.toJson());
    }

    public static void main(String[] args) throws IOException {
        DBInitiator db = new DBInitiator();
        db.addAllJsonsToDB();
        db.printDBDocuments("Users");
    }
}

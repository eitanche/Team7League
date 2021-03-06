package dataBase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class for initiate the mongoDataBase from local
 */
public class DBInitiator {
    private final String pathToResources = "src/main/resources";
    private MongoDatabase database;

    public DBInitiator() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("Database");
        database.drop();
    }

    /**
     * adding JSON files to DB
     */
    public void addAllJsonsToDB() throws IOException {
         for (String jsonFileName: listFilesUsingJavaIO(pathToResources)) {
             loadDBFromJsonFile(jsonFileName);
         }
    }

    /**
     * collect all files from project's resources into set
     * @param dir - path to resources
     * @return - Set of file names
     */
    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    /**
     * insert jsonFile to the DB
     * @param jsonFileName - the name of the json file
     */
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

    /**
     * initiating the DB with all the json files in the resources
     */
    public static void initiateDB() throws IOException {
        DBInitiator db = new DBInitiator();
        db.addAllJsonsToDB();
    }
}

package dataBase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
//import org.junit.Before;
import org.testng.annotations.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DBInitiatorTest {
    private static MongoDatabase database;
    private static String pathToJsons = "src/main/resources/";

    @BeforeClass
    public static void initiateDB() throws IOException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("Database");
        database.drop();
        DBInitiator.initiateDB();
        DBInitiatorTest.database = client.getDatabase("Database");
    }

    @Test
    public void checkTablesExist() {
        HashSet<String> expectedCollectionNames = new HashSet<> (Arrays.asList("Referees", "Teams", "Fans", "Leagues", "Seasons", "AssociationMembers", "Users", "Players", "SystemManagers"));
        HashSet<String> actualCollectionNames = new HashSet<>();
        for (String collectionName: database.listCollectionNames())
            actualCollectionNames.add(collectionName);
        assertEquals(expectedCollectionNames,actualCollectionNames);
    }

    @Test
    public void checkRefereesTables() throws FileNotFoundException {
        checkTableContents("Referees");
    }

    @Test
    public void checkTeamsTables() throws FileNotFoundException {
        checkTableContents("Teams");
    }

    @Test
    public void checkFansTables() throws FileNotFoundException {
        checkTableContents("Fans");
    }

    @Test
    public void checkLeaguesTables() throws FileNotFoundException {
        checkTableContents("Leagues");
    }

    @Test
    public void checkSeasonsTables() throws FileNotFoundException {
        checkTableContents("Seasons");
    }

    @Test
    public void checkAssociationMembersTables() throws FileNotFoundException {
        checkTableContents("AssociationMembers");
    }

    @Test
    public void checkUsersTables() throws FileNotFoundException {
        checkTableContents("Users");
    }

    @Test
    public void checkPlayersTables() throws FileNotFoundException {
        checkTableContents("Players");
    }

    @Test
    public void checkSystemManagersTables() throws FileNotFoundException {
        checkTableContents("SystemManagers");
    }

    private void checkTableContents(String tableName) throws FileNotFoundException {
        String pathToFile = pathToJsons + tableName.toLowerCase(Locale.ROOT) + ".json";
        File myObj = new File(pathToFile);
        Scanner myReader = new Scanner(myObj);
        MongoCollection<Document> collection = database.getCollection(tableName);
        int numberOfLines = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            Document doc = Document.parse(line);
            numberOfLines++;
            assertTrue(tableContainsDocument(collection, doc));
        }
        assertEquals(numberOfLines, collection.countDocuments());
    }

    private boolean tableContainsDocument(MongoCollection collection, Document doc) {
        return collection.find(doc).first()!=null;
    }



}

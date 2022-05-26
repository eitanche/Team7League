package dataBase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
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

/**
 * test class to test the DBInitiator
 */
@RunWith(JUnit4.class)
public class DBInitiatorTest {
    private static MongoDatabase database;
    private static String pathToJsons = "src/main/resources/";

    /**
     * initiate DB from local
     */
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

    /**
     * check if all tables exists in DB
     */
    @Test
    public void checkTablesExist() {
        HashSet<String> expectedCollectionNames = new HashSet<> (Arrays.asList("Referees", "Teams", "Fans", "Leagues", "Seasons", "AssociationMembers", "Users", "Players", "SystemManagers"));
        HashSet<String> actualCollectionNames = new HashSet<>();
        for (String collectionName: database.listCollectionNames())
            actualCollectionNames.add(collectionName);
        assertEquals(expectedCollectionNames,actualCollectionNames);
    }

    /**
     * check if referee's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkRefereesTables() throws FileNotFoundException {
        checkTableContents("Referees");
    }


    /**
     * check if team's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkTeamsTables() throws FileNotFoundException {
        checkTableContents("Teams");
    }


    /**
     * check if fans's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkFansTables() throws FileNotFoundException {
        checkTableContents("Fans");
    }


    /**
     * check if league's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkLeaguesTables() throws FileNotFoundException {
        checkTableContents("Leagues");
    }


    /**
     * check if season's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkSeasonsTables() throws FileNotFoundException {
        checkTableContents("Seasons");
    }


    /**
     * check if associationMember's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkAssociationMembersTables() throws FileNotFoundException {
        checkTableContents("AssociationMembers");
    }


    /**
     * check if users's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkUsersTables() throws FileNotFoundException {
        checkTableContents("Users");
    }


    /**
     * check if players's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkPlayersTables() throws FileNotFoundException {
        checkTableContents("Players");
    }


    /**
     * check if systemManagers's table in the DB contain all details from the json content
     * @throws FileNotFoundException - throws if file not found
     */
    @Test
    public void checkSystemManagersTables() throws FileNotFoundException {
        checkTableContents("SystemManagers");
    }

    /**
     * check if the table in the DB contain all the json content
     * @param tableName - table's name of the desired table
     * @throws FileNotFoundException - throws if file not found
     */
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

    /**
     * check if table in DB contain a record
     * @param collection - table from DB
     * @param doc - record to check if exist
     * @return - True if record exists in the table, else False
     */
    private boolean tableContainsDocument(MongoCollection collection, Document doc) {
        return collection.find(doc).first()!=null;
    }



}

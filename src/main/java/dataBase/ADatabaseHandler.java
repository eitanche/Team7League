package dataBase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.LoggerFactory;

/**
 * abstract class for handling with the DB
 */
public abstract class ADatabaseHandler {
    protected MongoDatabase database;

    protected ADatabaseHandler () {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("Database");
    }
}

package dataBase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public abstract class ADatabaseHandler {
    protected MongoDatabase database;

    protected ADatabaseHandler () {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("Database");
    }
}

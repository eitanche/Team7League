package dataBase.Loaders;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import domain.Subscriptions.Subscription;
import org.bson.Document;

public class SubscriptionLoader extends ADatabaseHandler implements ILoader {
    @Override
    public Subscription authenticate(String userName, String hashedPassword) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("userName", userName);
        desiredUserDocument.put("password", hashedPassword);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        if (result == null)
            return null;
        return SubscriptionFactory.getSubscriptionObject((String)result.get("name"), ((String[])result.get("role"))[0]);
    }

    @Override
    public boolean isUserExists(String userName) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("userName", userName);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        return result!=null;
    }
}

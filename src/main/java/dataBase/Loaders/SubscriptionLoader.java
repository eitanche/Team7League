package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.Subscriptions.Subscription;
import org.bson.Document;

import java.util.ArrayList;

public class SubscriptionLoader extends ADatabaseHandler implements ISubscriptionLoader {
    private static SubscriptionLoader instance=null;

    private SubscriptionLoader() {
        super();
    }


    public static SubscriptionLoader getInstance() {
        if (instance==null)
            instance = new SubscriptionLoader();
        return instance;
    }

    @Override
    public Subscription authenticate(String userName, String hashedPassword) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("userName", userName);
        desiredUserDocument.put("password", hashedPassword);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        if (result == null)
            return null;
        return SubscriptionFactory.getSubscriptionObject((String)result.get("_id"), (String)result.get("name"), (String) ((ArrayList)result.get("roles")).get(0));
    }

    @Override
    public boolean isUserExists(String userName) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("userName", userName);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        return result!=null;
    }

    public String getUserNameByID(String userID) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("_id", userID);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        if (result==null)
            return null;
        return (String) result.get("name");
    }
}

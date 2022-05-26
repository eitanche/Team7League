package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.Subscriptions.Subscription;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Singelton class for load inherited Subscription class from DB
 */
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

    /**
     * authenticate userName and password with user in DB in order to login to the system
     * @param userName - userName of the user who want to login
     * @param hashedPassword - password of the user who want to login
     * @return inherited Subscription class according the role of the User
     */
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

    /**
     * checking if the user is existing in the system
     * @param userName - userName to check
     * @return - True if the user is exist, else False
     */
    @Override
    public boolean isUserExists(String userName) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("userName", userName);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        return result!=null;
    }

    /**
     * load user name from DB with userId
     * @param userID - userId of the desired user
     * @return - name of the desired user
     */
    public String getUserNameByID(String userID) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("_id", userID);
        Document result = database.getCollection("Users").find(desiredUserDocument).first();
        if (result==null)
            return null;
        return (String) result.get("name");
    }
}

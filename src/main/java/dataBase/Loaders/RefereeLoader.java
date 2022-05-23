package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.Subscriptions.Referee;
import org.bson.Document;

import java.util.ArrayList;

public class RefereeLoader extends ADatabaseHandler implements IRefereeLoader{
    private static RefereeLoader instance=null;

    private RefereeLoader() {
        super();
    }

    public static RefereeLoader getInstance() {
        if (instance==null)
            instance = new RefereeLoader();
        return instance;
    }

    public Referee getReferee(String refereeID) {
        Document desiredReferee = new Document();
        desiredReferee.put("_id", refereeID);
        desiredReferee = database.getCollection("Referees").find(desiredReferee).first();
        if (desiredReferee==null)
            return null;
        return new Referee((String)desiredReferee.get("_id") , SubscriptionLoader.getInstance().getUserNameByID((String) desiredReferee.get("_id")));
    }

    public ArrayList<Referee> getAllReferees() {
        ArrayList<Referee> allReferees = new ArrayList<>();
        for (Document refereeDoc: database.getCollection("Referees").find(new Document())) {
            allReferees.add(new Referee((String)refereeDoc.get("_id"), SubscriptionLoader.getInstance().getUserNameByID((String) refereeDoc.get("_id"))));
        }
        return allReferees;
    }
}

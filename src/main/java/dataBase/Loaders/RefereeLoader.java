package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.Subscriptions.Referee;
import org.bson.Document;

import java.util.ArrayList;

public class RefereeLoader extends ADatabaseHandler {
    private static RefereeLoader instance=null;

    private RefereeLoader() {
        super();
    }

    public static RefereeLoader getInstance() {
        if (instance==null)
            instance = new RefereeLoader();
        return instance;
    }

    public Referee getReferee(String teamID) {
        Document desiredReferee = new Document();
        desiredReferee.put("_id", teamID);
        desiredReferee = database.getCollection("Referees").find(desiredReferee).first();
        if (desiredReferee==null)
            return null;
        return new Referee(SubscriptionLoader.getInstance().getUserNameByID((String) desiredReferee.get("user_id")));
    }

    public ArrayList<Referee> getAllReferees() {
        ArrayList<Referee> allReferees = new ArrayList<>();
        for (Document refereeDoc: database.getCollection("Referees").find(new Document())) {
            allReferees.add(new Referee(SubscriptionLoader.getInstance().getUserNameByID((String) refereeDoc.get("_id"))));
        }
        return allReferees;
    }
}

package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.LeagueComponents.League;
import org.bson.Document;

import java.util.ArrayList;

public class LeagueLoader extends ADatabaseHandler {
    private static LeagueLoader instance=null;

    private LeagueLoader() {
        super();
    }

    public static LeagueLoader getInstance() {
        if (instance==null)
            instance = new LeagueLoader();
        return instance;
    }

    public ArrayList<League> getLeagues() {
        ArrayList<League> allLeagues = new ArrayList<>();
        for (Document leagueDocument: database.getCollection("Leagues").find(new Document())) {
            allLeagues.add(new League((String)leagueDocument.get("_id"), (String)leagueDocument.get("name"), (String[])leagueDocument.get("seasons")));
        }
        return allLeagues;
    }

}

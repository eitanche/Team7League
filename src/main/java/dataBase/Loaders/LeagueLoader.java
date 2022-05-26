package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.LeagueComponents.League;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Singelton class for load leagues from DB
 */
public class LeagueLoader extends ADatabaseHandler implements ILeagueLoader {
    private static LeagueLoader instance=null;

    private LeagueLoader() {
        super();
    }

    public static LeagueLoader getInstance() {
        if (instance==null)
            instance = new LeagueLoader();
        return instance;
    }

    /**
     * collect all leagues from DB into ArrayList
     * @return ArrayList<League> of Leagues
     */

    public ArrayList<League> getLeagues() {
        ArrayList<League> allLeagues = new ArrayList<>();
        for (Document leagueDocument: database.getCollection("Leagues").find(new Document())) {
            allLeagues.add(new League((String)leagueDocument.get("_id"), (String)leagueDocument.get("name"), (ArrayList)leagueDocument.get("seasons")));
        }
        return allLeagues;
    }

}

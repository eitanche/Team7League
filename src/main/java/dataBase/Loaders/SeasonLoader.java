package dataBase.Loaders;

import domain.LeagueComponents.League;
import domain.LeagueComponents.Season;
import org.bson.Document;

import java.util.ArrayList;

public class SeasonLoader extends ADatabaseHandler{
    private static SeasonLoader instance=null;

    private SeasonLoader() {
        super();
    }

    public SeasonLoader getInstance() {
        if (instance==null)
            instance = new SeasonLoader();
        return instance;
    }

    private Season getSeason(String seasonID) {
        Document desiredSeason = new Document();
        desiredSeason.put("_id", seasonID);
        for (Document leagueDocument: database.getCollection("Seasons").find(desiredSeason)) {
            allSeasons.add(new League((String)leagueDocument.get("name"), (String[])leagueDocument.get("seasons")));
        }
        return allSeasons;
    }
}

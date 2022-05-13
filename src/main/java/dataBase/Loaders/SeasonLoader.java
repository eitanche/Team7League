package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.LeagueComponents.Season;
import org.bson.Document;

public class SeasonLoader extends ADatabaseHandler {
    private static SeasonLoader instance=null;

    private SeasonLoader() {
        super();
    }

    public static SeasonLoader getInstance() {
        if (instance==null)
            instance = new SeasonLoader();
        return instance;
    }

    public Season getSeason(String seasonID) {
        Document desiredSeason = new Document();
        desiredSeason.put("_id", seasonID);
        desiredSeason = database.getCollection("Seasons").find(desiredSeason).first();
        if (desiredSeason==null)
            return null;
        return new Season((String)desiredSeason.get("_id"), (String)desiredSeason.get("name"), (String[])desiredSeason.get("teams"), (String[])desiredSeason.get("referees"), (String)desiredSeason.get("gamePolicy"));
    }
}

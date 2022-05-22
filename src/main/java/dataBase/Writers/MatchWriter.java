package dataBase.Writers;

import com.mongodb.client.MongoCollection;
import dataBase.ADatabaseHandler;
import domain.LeagueComponents.Match;
import org.bson.Document;

import java.util.ArrayList;

public class MatchWriter extends ADatabaseHandler implements IMatchWriter {
    private static MatchWriter instance=null;

    private MatchWriter() {
        super();
    }

    public static MatchWriter getInstance() {
        if (instance==null)
            instance = new MatchWriter();
        return instance;
    }

    public void registerMatches(ArrayList<Match> matches) {
        MongoCollection<Document> matchCollection = database.getCollection("Matches");
        for(Match match: matches) {
            Document matchDocument = new Document();
            matchDocument.put("_id", match.getId());
            matchDocument.put("home_team", match.getHomeTeam().getId());
            matchDocument.put("away_team", match.getAwayTeam().getId());
            matchDocument.put("season",match.getSeason().getId());
            matchCollection.insertOne(matchDocument);
        }
    }
}

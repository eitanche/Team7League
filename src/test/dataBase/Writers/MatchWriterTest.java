package dataBase.Writers;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dataBase.DBInitiator;
import dataBase.Loaders.SeasonLoader;
import dataBase.Loaders.TeamLoader;
import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.LeagueComponents.Team;
import org.bson.Document;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MatchWriterTest {
    private static MatchWriter writer = MatchWriter.getInstance();
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    @Test
    public void registerMatches() {
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(TeamLoader.getInstance().getTeam("0"));
        teams.add(TeamLoader.getInstance().getTeam("1"));
        teams.add(TeamLoader.getInstance().getTeam("2"));
        teams.add(TeamLoader.getInstance().getTeam("3"));
        Season season = SeasonLoader.getInstance().getSeason("0");
        for (int i =0; i< teams.size(); i++) {
            for (int j =0; j< teams.size(); j++) {
                if (i!=j) {
                    matches.add(new Match(teams.get(i), teams.get(j), season));
                }
            }
        }
        writer.registerMatches(matches);
        assertTrue(DBContainsAllMatches(matches));
    }

    private boolean DBContainsAllMatches(ArrayList<Match> matches) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("Database");
        MongoCollection<Document> matchDB = database.getCollection("Matches");
        for (Match match: matches) {
            Document queryMatchDocument = new Document();
            queryMatchDocument.put("_id", match.getId());
            queryMatchDocument.put("home_team", match.getHomeTeam().getId());
            queryMatchDocument.put("away_team", match.getAwayTeam().getId());
            queryMatchDocument.put("season",match.getSeason().getId());
            if (matchDB.find(queryMatchDocument).first()==null)
                return false;
        }
        return true;
    }
}

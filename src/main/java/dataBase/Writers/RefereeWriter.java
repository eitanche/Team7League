package dataBase.Writers;

import com.mongodb.client.MongoCollection;
import dataBase.ADatabaseHandler;
import Exceptions.SeasonNotExistsException;
import domain.LeagueComponents.Season;
import domain.Subscriptions.Referee;
import org.bson.Document;

import java.util.List;

/**
 * Singelton class for write Matches to DB
 */
public class RefereeWriter extends ADatabaseHandler implements IRefereeWriter {
    private static RefereeWriter instance=null;

    private RefereeWriter() {
        super();
    }

    public static RefereeWriter getInstance() {
        if (instance==null)
            instance = new RefereeWriter();
        return instance;
    }

    /**
     * write referee into season in the DB
     * @param season - Season object to register a referee
     * @param referee - desired referee to register
     * @throws SeasonNotExistsException - exception if season is not exist
     */
    public void addRefereeToSeason(Season season, Referee referee) throws SeasonNotExistsException {
        MongoCollection<Document> seasonCollection = database.getCollection("Seasons");
        Document desiredSeason = new Document();
        desiredSeason.put("_id", season.getId());
        desiredSeason = seasonCollection.findOneAndDelete(desiredSeason);
        if (desiredSeason==null)
            throw new SeasonNotExistsException(season);
        List<String> refereeList = (List<String>) desiredSeason.remove("referees");
        refereeList.add(referee.getId());
        desiredSeason.put("referees", refereeList);
        seasonCollection.insertOne(desiredSeason);

    }

}

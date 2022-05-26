package dataBase.Writers;
import dataBase.DBInitiator;
import Exceptions.SeasonNotExistsException;
import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.SeasonLoader;
import domain.LeagueComponents.Season;
import domain.Subscriptions.Referee;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * this class tests the RefereeWriter class.
 */

public class RefereeWriterTest {
    private static RefereeWriter writer = RefereeWriter.getInstance();
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    /**
     * check if the DB add referee correctly.
     * @throws SeasonNotExistsException
     */
    @Test
    public void addRefereeToSeason_validSeason() throws SeasonNotExistsException {
        Season s = SeasonLoader.getInstance().getSeason("0");
        Referee r = RefereeLoader.getInstance().getReferee("7");
        writer.addRefereeToSeason(s, r);
        assertTrue(isRefereeInSeasonDB(s,r));
    }

    /**
     * checks if the DB throws exception if we try to add referee to season that isn't exist
     * @throws SeasonNotExistsException
     */
    @Test
    public void addRefereeToSeason_invalidSeason() throws SeasonNotExistsException {
        exceptionRule.expect(SeasonNotExistsException.class);
        ArrayList<String> teamIDs = new ArrayList<String>(
                Arrays.asList("10","11","12","13","14","15","16","17","18","19"));
        ArrayList<String> refereeIDs = new ArrayList<String>(
                Arrays.asList("5","6","7","8","9"));
        Season s = new Season("1212","12121", teamIDs, refereeIDs, "regular");
        Referee r = RefereeLoader.getInstance().getReferee("7");
        writer.addRefereeToSeason(s, r);
    }

    /**
     *
     * @param season
     * @param referee
     * @return boolean value represents if the referee attached to the param season or not.
     */
    private boolean isRefereeInSeasonDB(Season season, Referee referee) {
        Season newSeasonFromDB = SeasonLoader.getInstance().getSeason(season.getId());
        return newSeasonFromDB.getReferees().contains(referee);
    }

}

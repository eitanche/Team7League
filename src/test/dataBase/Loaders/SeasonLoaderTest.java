package dataBase.Loaders;

import dataBase.DBInitiator;
import domain.LeagueComponents.Season;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * this class tests the SeasonLoader class.
 */
public class SeasonLoaderTest {
    private static SeasonLoader loader = SeasonLoader.getInstance();
    /**
     * initiate DB.
     * @throws IOException
     */
    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    /**
     * checks if the specific season loads correctly.
     */
    @Test
    public void getSeason_validSeason() {
        ArrayList<String> teamIDs = new ArrayList<String>(
                Arrays.asList("10","11","12","13","14","15","16","17","18","19"));
        ArrayList<String> refereeIDs = new ArrayList<String>(
                Arrays.asList("5","6","7","8","9"));
        Season expectedSeason = new Season("1", "2021-2022", teamIDs, refereeIDs, "regular");
        Season actualSeason = loader.getSeason("1");
        assertEquals(expectedSeason, actualSeason);
    }
    /**
     * checks if there is no such a season in the DB if returns null.
     * one object test.
     */
    @Test
    public void getSeason_invalidSeason() {
        Season actualSeason = loader.getSeason("1212");
        assertNull(actualSeason);
    }
}

package dataBase.Loaders;
import dataBase.DBInitiator;
import domain.LeagueComponents.League;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * this class tests the LeagueLoader class.
 */
public class LeagueLoaderTest {
    private static LeagueLoader loader = LeagueLoader.getInstance();

    /**
     * initiate DB.
     * @throws IOException
     */
    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    /**
     * checks if the leagues loads correctly.
     */
    @Test
    public void getLeagues() {
        ArrayList<League> expectedLeagues = new ArrayList<League>(
                Arrays.asList(new League("0","Ligat Ha-al", new ArrayList<String>(List.of("0"))),
                        new League("1","Super League", new ArrayList<String>(List.of("1")))));
        ArrayList<League> actualLeagues = loader.getLeagues();
        assertEquals(expectedLeagues, actualLeagues);
    }
}

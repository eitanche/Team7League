package dataBase.Loaders;

import dataBase.DBInitiator;
import domain.LeagueComponents.Team;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * this class tests the SeasonLoader class.
 */

public class TeamLoaderTest {
    private static TeamLoader loader = TeamLoader.getInstance();
    /**
     * initiate DB.
     * @throws IOException
     */
    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }
    /**
     * checks if the specific team loads correctly.
     */
    @Test
    public void getTeam_validTeam() {
        Team expectedTeam = new Team("1", "Maccabi Haifa");
        Team actualTeam = loader.getTeam("1");
        assertEquals(expectedTeam, actualTeam);
    }

    /**
     * checks if there is no such a team in the DB if returns null.
     */
    @Test
    public void getTeam_invalidTeam() {
        Team actualTeam = loader.getTeam("21");
        assertNull(actualTeam);
    }
}

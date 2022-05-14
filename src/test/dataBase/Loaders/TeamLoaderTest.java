package dataBase.Loaders;

import dataBase.DBInitiator;
import domain.LeagueComponents.Team;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TeamLoaderTest {
    private static TeamLoader loader = TeamLoader.getInstance();

    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    @Test
    public void getTeam_validTeam() {
        Team expectedTeam = new Team("1", "Maccabi Haifa");
        Team actualTeam = loader.getTeam("1");
        assertEquals(expectedTeam, actualTeam);
    }

    @Test
    public void getTeam_invalidTeam() {
        Team actualTeam = loader.getTeam("21");
        assertNull(actualTeam);
    }
}

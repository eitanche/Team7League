package dataBase.Loaders;

import dataBase.DBInitiator;
import domain.Subscriptions.Referee;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * this class tests the RefereeLoader class.
 */
public class RefereeLoaderTest {
    private static RefereeLoader loader = RefereeLoader.getInstance();

    /**
     * initiate DB.
     * @throws IOException
     */
    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    /**
     * checks if the specific referee loads correctly.
     */
    @Test
    public void getReferee_validReferee() {
        Referee actualReferee = loader.getReferee("2");
        Referee expectedReferee = new Referee("2", "Mot Eilohim");
        assertEquals(expectedReferee, actualReferee);
    }

    /**
     * checks if there is no such a referee in the DB if returns null.
     * one object test.
     */
    @Test
    public void getReferee_invalidReferee() {
        Referee actualReferee = loader.getReferee("222");
        assertNull(actualReferee);
    }

    /**
     * checks if all the referees loads correctly.
     * all objects test.
     */
    @Test
    public void getAllReferees() {
        ArrayList<Referee> expectedReferees = new ArrayList<Referee>(
                Arrays.asList(new Referee("0","Erez Levi"),
                        new Referee("1","Eli Hakmon"),
                        new Referee("2","Mot Eilohim"),
                        new Referee("3","yosi shitrit"),
                        new Referee("4","Sapir Berman"),
                        new Referee("5","Roei Alon"),
                        new Referee("6","Raz Cohen"),
                        new Referee("7","Rotem Morad"),
                        new Referee("8","Yoni Bolili"),
                        new Referee("9","Rich Puce")));
        ArrayList<Referee> actualReferees = loader.getAllReferees();
        assertEquals(expectedReferees, actualReferees);

    }
}

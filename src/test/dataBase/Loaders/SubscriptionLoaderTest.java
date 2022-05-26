package dataBase.Loaders;

import dataBase.DBInitiator;
import domain.Subscriptions.Referee;
import domain.Subscriptions.Subscription;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * this class tests the SubscriptionLoader class.
 */


public class SubscriptionLoaderTest {
    private static SubscriptionLoader loader = SubscriptionLoader.getInstance();

    /**
     * initiate DB.
     * @throws IOException
     */
    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    /**
     * checks if the DB authenticate credentials correctly when :
     *      userName: valid
     *      password: valid
     */
    @Test
    public void authenticate_validUserValidPassword() {
        Subscription actualSubscription = loader.authenticate("yb", "9");
        assertNotNull(actualSubscription);
        assertEquals("8", actualSubscription.getId());
        assertEquals("Yoni Bolili", actualSubscription.getName());
        assertTrue(actualSubscription instanceof Referee);
    }

    /**
     * checks if the DB authenticate credentials correctly when :
     *      userName: valid
     *      password: invalid
     */
    @Test
    public void authenticate_validUserInvalidPassword() {
        Subscription actualSubscription = loader.authenticate("yb", "10");
        assertNull(actualSubscription);
    }
    /**
     * checks if the DB authenticate credentials correctly when :
     *      userName: invalid
     *      password: valid
     */
    @Test
    public void authenticate_invalidUser() {
        Subscription actualSubscription = loader.authenticate("yasdasb", "10");
        assertNull(actualSubscription);
    }
    /**
     * checks if the DB find existing user
     *      userName: valid
     */
    @Test
    public void isUserExists_validUser() {
        assertTrue(loader.isUserExists("eh"));
    }
    /**
     * checks if the DB find not exist user
     *      userName: invalid
     */
    @Test
    public void isUserExists_invalidUser() {
        assertFalse(loader.isUserExists("ehasdaasd"));
    }
    /**
     * checks if the DB find userName by Id
     *      userName: valid
     */
    @Test
    public void getUserNameByID_validUser() {
        assertEquals("Eli Hakmon" ,loader.getUserNameByID("1"));
    }
    /**
     * checks if the DB dont find userName by Id because it dosent exists
     *      userName: invalid
     */
    @Test
    public void getUserNameByID_invalidUser() {
        assertNull(loader.getUserNameByID("1121"));
    }
}

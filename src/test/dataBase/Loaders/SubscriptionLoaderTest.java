package dataBase.Loaders;

import dataBase.DBInitiator;
import domain.Subscriptions.Referee;
import domain.Subscriptions.Subscription;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SubscriptionLoaderTest {
    private static SubscriptionLoader loader = SubscriptionLoader.getInstance();

    @BeforeClass
    public static void initiateEmptyDB() throws IOException {
        DBInitiator.initiateDB();
    }

    @Test
    public void authenticate_validUserValidPassword() {
        Subscription actualSubscription = loader.authenticate("yb", "9");
        assertNotNull(actualSubscription);
        assertEquals("8", actualSubscription.getId());
        assertEquals("Yoni Bolili", actualSubscription.getName());
        assertTrue(actualSubscription instanceof Referee);
    }

    @Test
    public void authenticate_validUserInvalidPassword() {
        Subscription actualSubscription = loader.authenticate("yb", "10");
        assertNull(actualSubscription);
    }

    @Test
    public void authenticate_invalidUser() {
        Subscription actualSubscription = loader.authenticate("yasdasb", "10");
        assertNull(actualSubscription);
    }

    @Test
    public void isUserExists_validUser() {
        assertTrue(loader.isUserExists("eh"));
    }

    @Test
    public void isUserExists_invalidUser() {
        assertFalse(loader.isUserExists("ehasdaasd"));
    }

    @Test
    public void getUserNameByID_validUser() {
        assertEquals("Eli Hakmon" ,loader.getUserNameByID("1"));
    }

    @Test
    public void getUserNameByID_invalidUser() {
        assertNull(loader.getUserNameByID("1121"));
    }
}

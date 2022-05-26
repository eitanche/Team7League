package dataBase.Loaders;

import domain.Subscriptions.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * this class tests the SubscriptionFactory class.
 * It have to create the correct instances.
 */

public class SubscriptionFactoryTest {

    @Test
    public void getSubscriptionObject_AssociationMember() {
        Subscription s = SubscriptionFactory.getSubscriptionObject("1", "eitan", "associationMember");
        assertTrue(s instanceof AssociationMember);
        assertEquals("eitan", s.getName());
        assertEquals("1", s.getId());
    }

    @Test
    public void getSubscriptionObject_Player() {
        Subscription s = SubscriptionFactory.getSubscriptionObject("1", "eitan", "Player");
        assertTrue(s instanceof Player);
        assertEquals("eitan", s.getName());
        assertEquals("1", s.getId());
    }

    @Test
    public void getSubscriptionObject_Referee() {
        Subscription s = SubscriptionFactory.getSubscriptionObject("1", "eitan", "Referee");
        assertTrue(s instanceof Referee);
        assertEquals("eitan", s.getName());
        assertEquals("1", s.getId());
    }

    @Test
    public void getSubscriptionObject_SystemManager() {
        Subscription s = SubscriptionFactory.getSubscriptionObject("1", "eitan", "SystemManager");
        assertTrue(s instanceof SystemManager);
        assertEquals("eitan", s.getName());
        assertEquals("1", s.getId());
    }

    @Test
    public void getSubscriptionObject_invalidRole() {
        Subscription s = SubscriptionFactory.getSubscriptionObject("1", "eitan", "asdasdasdasd");
        assertNull(s);
    }
}

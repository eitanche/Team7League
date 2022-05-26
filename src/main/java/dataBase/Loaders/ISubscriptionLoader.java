package dataBase.Loaders;

import domain.Subscriptions.Subscription;

/**
 * interface for SubscriptionLoader
 */
public interface ISubscriptionLoader {

    Subscription authenticate(String userName, String hashedPassword);

    boolean isUserExists(String userName);
}

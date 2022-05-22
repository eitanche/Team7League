package dataBase.Loaders;

import domain.Subscriptions.Subscription;

public interface ISubscriptionLoader {
    Subscription authenticate(String userName, String hashedPassword);

    boolean isUserExists(String userName);
}

package services.useCases;

import dataBase.IDbHandler;
import dataBase.Loaders.AssociationMemberLoader;
import dataBase.Loaders.ILoader;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;

public class UC2GameScheduler {

    private SubscriptionLoader loader;
    private AssociationMember am;

    public UC2GameScheduler(ILoader userDb) {
        am = loader.readFromDB("associationmember"); // need to create from the dbHandler
    }

    public void GameScheduler(){

        if (!checkConditions())
            return;

        am.assignAutoSeasonMatches(); // lahaz al shibuz mishakim


    }

    private boolean checkConditions() {
        return true;
    }
}

package services.useCases;

import dataBase.IDbHandler;
import dataBase.Loaders.AssociationMemberLoader;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;

public class UC2GameScheduler {

    private AssociationMember am;

    public UC2GameScheduler(AssociationMember am) {
        this.am = am; // need to create from the dbHandler
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

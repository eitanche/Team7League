package services;

import dataBase.IDbHandler;
import domain.AssociationMember;

public class UC2GameScheduler {


    public void GameScheduler(AssociationMember am){

        if (!checkConditions())
            return;

        am.assignAutoSeasonMatches(); // lahaz al shibuz mishakim


    }

    private boolean checkConditions() {
        return true;
    }
}

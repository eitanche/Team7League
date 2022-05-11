package services;

import dataBase.IDbHandler;
import domain.AssociationMember;

public class UC2GameScheduler {

    private IDbHandler userDb;
    private AssociationMember am;

    public UC2GameScheduler(IDbHandler userDb) {
        this.userDb = userDb;
        am = new AssociationMember(); // need to create from the dbHandler
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

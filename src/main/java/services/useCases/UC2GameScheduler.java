package services.useCases;

import dataBase.Loaders.LeagueLoader;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;

public class UC2GameScheduler {

    private AssociationMember am;

    public UC2GameScheduler(Subscription loggedInUser) throws NotAssociationMemberException {
        if (!(loggedInUser instanceof AssociationMember))
            throw new NotAssociationMemberException();
        am = (AssociationMember) loggedInUser;
    }

    public void GameScheduler(){

        if (!checkConditions())
            return;
        am.assignAutoSeasonMatches();


    }

    private boolean checkConditions() {
        if(LeagueLoader.getInstance().getLeagues().isEmpty())
            return false;
        return true;
    }
}

package services.useCases;

import dataBase.Loaders.ILoader;
import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.SeasonLoader;
import domain.LeagueComponents.League;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Referee;
import domain.Subscriptions.Subscription;

import java.util.ArrayList;

public class UC1RefereeRegistration {

    private AssociationMember am;

    public UC1RefereeRegistration(Subscription am) throws NotAssociationMemberException {
        if (am instanceof AssociationMember)
            this.am = (AssociationMember)am;
        else
            throw new NotAssociationMemberException();
    }

    public void RefereeRegistration() {
        if (!checkConditions())
            return;

        am.assignReferees(); // lahaz al rishum shoftim
    }

    private boolean checkConditions() {

        if (RefereeLoader.getInstance().getAllReferees().isEmpty()) {
            System.out.println("There Are No Referees In The System");
            return false;
        }
        if (LeagueLoader.getInstance().getLeagues().isEmpty()) {
            System.out.println("There Are No Leagues In The System");
            return false;
        }
        if (SeasonLoader.getInstance().getSeason("0") == null) {
            System.out.println("There Are No Seasons In The System");
            return false;
        }

        return true;
    }


}

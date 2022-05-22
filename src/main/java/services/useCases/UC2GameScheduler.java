package services.useCases;

import Exceptions.InvalidNumberOfTeamsException;
import Exceptions.NotAssociationMemberException;
import dataBase.Loaders.LeagueLoader;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;

import java.util.ArrayList;
import java.util.Scanner;

public class UC2GameScheduler {

    private AssociationMember am;

    public UC2GameScheduler(Subscription loggedInUser) throws NotAssociationMemberException {
        if (!(loggedInUser instanceof AssociationMember))
            throw new NotAssociationMemberException();
        am = (AssociationMember) loggedInUser;
    }

    public ArrayList<League> GameScheduler() throws InvalidNumberOfTeamsException {
        if (!checkConditions())
            return null;
        return chooseLeagueToAssignAutoSeasonMatches();
    }

    public void GameSchedulerSetScheduler(League choosedLeague) throws InvalidNumberOfTeamsException {
        assignAutoSeasonMatches(choosedLeague);
    }

    public boolean checkConditions() {
        if(LeagueLoader.getInstance().getLeagues().isEmpty()) {
            return false;
        }
        return true;
    }

    public void assignAutoSeasonMatches(League choosedLeague) throws InvalidNumberOfTeamsException {

        Season season = choosedLeague.getCurrentSeason();
        ArrayList<Match> matches = season.getGamePolicy().active();
//        printMatches(matches);
        season.setMatches(matches); // write in db and in the attribute class
    }

    public ArrayList<League> chooseLeagueToAssignAutoSeasonMatches() {

        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();
        if (leagues == null)
            throw new NullPointerException();
        return leagues;
    }


    public void printMatches(ArrayList<Match> matches) {
        for (int i = 0; i < matches.size(); i++){
            System.out.println(matches.get(i));
        }
    }
}

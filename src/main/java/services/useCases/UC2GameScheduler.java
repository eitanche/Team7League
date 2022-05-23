package services.useCases;

import Exceptions.InvalidNumberOfTeamsException;
import Exceptions.NotAssociationMemberException;
import dataBase.Loaders.ILeagueLoader;
import dataBase.Loaders.LeagueLoader;
import dataBase.Writers.IMatchWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;

import java.util.ArrayList;
import java.util.Scanner;

public class UC2GameScheduler {

    private AssociationMember am;
    private ILeagueLoader LeagueLoader;

    public UC2GameScheduler(Subscription loggedInUser, ILeagueLoader LeagueLoader) throws NotAssociationMemberException {
        this.LeagueLoader = LeagueLoader;
        if (!(loggedInUser instanceof AssociationMember))
            throw new NotAssociationMemberException();
        am = (AssociationMember) loggedInUser;
    }

    public ArrayList<League> GameScheduler(ILeagueLoader LeagueLoader) throws InvalidNumberOfTeamsException {
        if (!checkConditions(LeagueLoader))
            return null;
        return chooseLeagueToAssignAutoSeasonMatches(LeagueLoader);
    }

    public void GameSchedulerSetScheduler(League choosedLeague ,IMatchWriter matchWriter) throws InvalidNumberOfTeamsException {
        assignAutoSeasonMatches(choosedLeague, matchWriter);
    }

    public boolean checkConditions(ILeagueLoader LeagueLoader) {
        if(LeagueLoader.getLeagues().isEmpty()) {
            return false;
        }
        return true;
    }

    public void assignAutoSeasonMatches(League choosedLeague , IMatchWriter matchWriter) throws InvalidNumberOfTeamsException {

        Season season = choosedLeague.getCurrentSeason();
        ArrayList<Match> matches = season.getGamePolicy().active();
//        printMatches(matches);
        season.setMatches(matches, matchWriter); // write in db and in the attribute class
    }

    public ArrayList<League> chooseLeagueToAssignAutoSeasonMatches(ILeagueLoader LeagueLoader) {

        ArrayList<League> leagues = LeagueLoader.getLeagues();
        if (leagues == null)
            throw new NullPointerException();
        return leagues;
    }

//
//    public void printMatches(ArrayList<Match> matches) {
//        for (int i = 0; i < matches.size(); i++){
//            System.out.println(matches.get(i));
//        }
//    }


}

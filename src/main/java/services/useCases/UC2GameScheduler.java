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

/**
 * operate class to Scheduling Matches.
 * data members:
 * am - is the association member.
 * leageLoder - ILeaueLoader object that linked the data base to service leyer and pull the relevant data from data base.
 */
public class UC2GameScheduler {

    private AssociationMember am;
    private ILeagueLoader LeagueLoader;

    public UC2GameScheduler(Subscription loggedInUser, ILeagueLoader LeagueLoader) throws NotAssociationMemberException {
        this.LeagueLoader = LeagueLoader;
        if (!(loggedInUser instanceof AssociationMember))
            throw new NotAssociationMemberException();
        am = (AssociationMember) loggedInUser;
    }

    /**
     * This function choose the leagues which need to be scheduler games.
     * @param LeagueLoader ILeaueLoader object that linked the data base to service leyer and pull the relevant data from data base.
     * @return list of leagues that need to be scheduler matches.
     * @throws InvalidNumberOfTeamsException - exception to ensure the number of teams is correct.
     */
    public ArrayList<League> GameScheduler(ILeagueLoader LeagueLoader) throws InvalidNumberOfTeamsException {
        if (!checkConditions(LeagueLoader))
            return null;
        return chooseLeagueToAssignAutoSeasonMatches(LeagueLoader);
    }

    /**
     * This function call to set schedule function.
     * @param choosedLeague
     * @param matchWriter
     * @throws InvalidNumberOfTeamsException
     */
    public void GameSchedulerSetScheduler(League choosedLeague ,IMatchWriter matchWriter) throws InvalidNumberOfTeamsException {
        assignAutoSeasonMatches(choosedLeague, matchWriter);
    }

    /**
     * This function check the condtions of the use case.
     * check that have leagues in DB.
     * @param LeagueLoader
     * @return true if we have leagues in DB.
     */
    public boolean checkConditions(ILeagueLoader LeagueLoader) {
        if(LeagueLoader.getLeagues().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This function apply the scheduler of specific league in accordance the policy.
     * @param choosedLeague - the specific league.
     * @param matchWriter
     * @throws InvalidNumberOfTeamsException - exception to ensure the number of teams is correct.
     */
    public void assignAutoSeasonMatches(League choosedLeague , IMatchWriter matchWriter) throws InvalidNumberOfTeamsException {

        Season season = choosedLeague.getCurrentSeason();
        ArrayList<Match> matches = season.getGamePolicy().active();
//        printMatches(matches);
        season.setMatches(matches, matchWriter); // write in db and in the attribute class
    }

    /**
     * This fuction choose the relevant league to schedule the matches.
     * @param LeagueLoader
     * @return list of leagues that need to be scheduler matches.
     */
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

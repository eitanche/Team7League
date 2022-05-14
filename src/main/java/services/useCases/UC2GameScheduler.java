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

    public void GameScheduler() throws InvalidNumberOfTeamsException {
        if (!checkConditions())
            return;
        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        assignAutoSeasonMatches(choosedLeague);
    }

    public boolean checkConditions() {
        if(LeagueLoader.getInstance().getLeagues().isEmpty()) {
            System.out.println("There Are No Leagues In The System");
            return false;
        }
        return true;
    }

    public void assignAutoSeasonMatches(League choosedLeague) throws InvalidNumberOfTeamsException {

        Season season = choosedLeague.getCurrentSeason();
        ArrayList<Match> matches = season.getGamePolicy().active();
        printMatches(matches);
        season.setMatches(matches); // write in db and in the attribute class
    }

    public League chooseLeagueToAssignAutoSeasonMatches() {

        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();
        if (leagues == null)
            throw new NullPointerException();

        System.out.println("Please Choose League");
        for (int i = 0; i <  leagues.size(); i++) {
            System.out.println((i+1) + ". " + leagues.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputLeague = scanner.nextInt();
        return leagues.get(inputLeague - 1);
    }


    public void printMatches(ArrayList<Match> matches) {
        for (int i = 0; i < matches.size(); i++){
            System.out.println(matches.get(i));
        }
    }
}

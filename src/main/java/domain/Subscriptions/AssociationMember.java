package domain.Subscriptions;

import domain.LeagueComponents.League;
import domain.LeagueComponents.Season;

import java.util.ArrayList;
import java.util.Scanner;

public class AssociationMember extends Subscription {

    private IDbHandler userDb;
    private League[] leagues; // יש כמה ליגות פעילות בו-זמנית ?

    public AssociationMember(IDbHandler userDb, League[] leagues) {
        this.userDb = userDb;
        this.leagues = leagues;
    }

    public void assignAutoSeasonMatches() {

        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        choosedLeague.getCurrentSeason().getGamePolicy().active();

    }

    private League chooseLeagueToAssignAutoSeasonMatches() {

        System.out.println("Please Choose League");
        for (int i = 0; i <  leagues.length; i++) {
            System.out.println(leagues[i]);
        }
        Scanner scanner = new Scanner(System.in);
        int inputLeague = scanner.nextInt();

        return leagues[inputLeague];
    }

    public void assignReferees() {
        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        Season choosedSeason =  chooseSeasonToAssignReferees(choosedLeague);
        Referee choosedReferee = chooseRefereeToAssign(choosedSeason);

        // insert the referee as choosedSeason's referee in the database

    }

    private Season chooseSeasonToAssignReferees(League league) {

        ArrayList<Season> seasons =  league.getSeasons();

        System.out.println("Please Choose Season To Assign Referees");
        for (int i = 0; i <  seasons.size(); i++) {
            System.out.println(seasons.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputSeason = scanner.nextInt();

        return seasons.get(inputSeason);
    }

    private Referee chooseRefereeToAssign(Season season) {

        ArrayList<Referee> referees =  season.getReferees();

        System.out.println("Please Choose Referee To Assign");
        for (int i = 0; i <  referees.size(); i++) {
            System.out.println(referees.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputReferee = scanner.nextInt();

        return referees.get(inputReferee);
    }

}

package domain.Subscriptions;

import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.SeasonLoader;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Season;
import domain.SystemManagment;

import java.util.ArrayList;
import java.util.Scanner;

public class AssociationMember extends Subscription {

    private ArrayList<League> leagues; // יש כמה ליגות פעילות בו-זמנית ?

    public AssociationMember(String id, String name) {
        super(id, name);
        leagues = LeagueLoader.getInstance().getLeagues();
    }

    public void setLeagues(ArrayList<League> leagues) {
        this.leagues = leagues;
    }

    //UC2
    public void assignAutoSeasonMatches() {

        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        Season season = SeasonLoader.getInstance().getSeason(choosedLeague.getSeasonsIds()[0]);
        choosedLeague.setCurrentSeason(season);
        choosedLeague.getCurrentSeason().getGamePolicy().active();

        // עצרנו כאן אחרי שסידרנו את הטוען ליגות והטוען עונות(שטוען את העונה הנוכחית)

    }

    private League chooseLeagueToAssignAutoSeasonMatches() {

        System.out.println("Please Choose League");
        for (int i = 0; i <  leagues.size(); i++) {
            System.out.println(leagues.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputLeague = scanner.nextInt();

        return leagues.get(inputLeague);
    }
    //UC1
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

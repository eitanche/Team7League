package domain.Subscriptions;

import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.SeasonLoader;
import dataBase.Writers.RefereeWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Match;
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
        Season season = SeasonLoader.getInstance().getSeason(choosedLeague.getCurrentSeason().getId());
        choosedLeague.setCurrentSeason(season);
        ArrayList<Match> matchs = choosedLeague.getCurrentSeason().getGamePolicy().active();
        choosedLeague.getCurrentSeason().setMatchs(matchs);

        // עצרנו כאן אחרי שסידרנו את הטוען ליגות והטוען עונות(שטוען את העונה הנוכחית)

    }

    private League chooseLeagueToAssignAutoSeasonMatches() {

        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();

        System.out.println("Please Choose League");
        for (int i = 0; i <  leagues.size(); i++) {
            System.out.println((i+1) + ". " + leagues.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputLeague = scanner.nextInt();

        return leagues.get(inputLeague - 1);
    }
    //UC1
    public void assignReferees() {
        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        Season choosedSeason =  chooseSeasonToAssignReferees(choosedLeague);

        Referee choosedReferee = chooseRefereeToAssign();
        //TODO : insert the referee as choosedSeason's referee in the database
        RefereeWriter.getInstance().addRefereeToSeason(choosedSeason, choosedReferee);
        choosedReferee.setSeason(choosedSeason);
        System.out.println("Click 1 to assign more referee to the season\nClick 2 to exit");
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        while (result == 1){
            choosedReferee = chooseRefereeToAssign();
            // TODO : insert the referee as choosedSeason's referee in the database
            RefereeWriter.getInstance().addRefereeToSeason(choosedSeason, choosedReferee);
            choosedReferee.setSeason(choosedSeason);

            System.out.println("Click 1 for assign more referee to the season\nClick 2 for exit");
            result = scanner.nextInt();
        }

        System.out.println("The Use Case Finished");
    }

    private Season chooseSeasonToAssignReferees(League league) {

        ArrayList<Season> seasons =  league.getSeasons();

        System.out.println("Please Choose Season To Assign Referees");
        for (int i = 0; i <  seasons.size(); i++) {
            System.out.println((i+1) + ". " + seasons.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputSeason = scanner.nextInt();

        return seasons.get(inputSeason - 1);
    }

    private Referee chooseRefereeToAssign() {

        ArrayList<Referee> referees =  RefereeLoader.getInstance().getAllReferees();

        System.out.println("Please Choose Referee To Assign");
        for (int i = 0; i <  referees.size(); i++) {
            System.out.println((i+1) + ". " +referees.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputReferee = scanner.nextInt();

        return referees.get(inputReferee - 1);
    }

}

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


    public AssociationMember(String id, String name) {
        super(id, name);
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

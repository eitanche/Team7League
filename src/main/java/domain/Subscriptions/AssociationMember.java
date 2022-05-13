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

    //UC2
    public void assignAutoSeasonMatches() {

        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        Season season = choosedLeague.getCurrentSeason();
        ArrayList<Match> matches = season.getGamePolicy().active();
        printMatches(matches);
        season.setMatches(matches); // write in db
    }

    private void printMatches(ArrayList<Match> matches) {
        for (int i = 0; i < matches.size(); i++){
            System.out.println(matches.get(i));
        }
    }
}

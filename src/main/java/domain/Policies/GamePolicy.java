package domain.Policies;

import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.LeagueComponents.Team;

import java.util.ArrayList;

public class GamePolicy extends Policy {

    private Season season;
    private int numberOfTeams;
    private ArrayList<Match> matches;


    public ArrayList<Match> active() {

        ArrayList<Team> teams1 = this.season.getTeams();

        for (int i = 0; i < teams1.size(); i++){
            for (int j = i+1 ; j < teams1.size(); j++){
                matches.add(new Match(teams1.get(i),teams1.get(j), season));
            }
        }
        return matches;
    }
}

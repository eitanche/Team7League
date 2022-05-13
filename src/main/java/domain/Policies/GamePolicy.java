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

        Team[] teams1 = this.season.getTeams();

        for (int i = 0; i < teams1.length; i++){
            for (int j = i+1 ; j < teams1.length; j++){
                matches.add(new Match(teams1[i],teams1[j], season));
            }
        }
        return matches;
    }
}

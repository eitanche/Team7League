package domain.Policies;

import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.LeagueComponents.Team;
import Exceptions.InvalidNumberOfTeamsException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * this class is a game policy.
 * A season object must contain game policy for proper conduct.
 */

public class GamePolicy extends Policy {

    private Season season;
    private int numberOfTeams;
    private ArrayList<Match> matches;

    public GamePolicy(Season season) {
        numberOfTeams=10;
        this.season = season;
    }

    /**
     * checks if there is exactly 10 teams in the season and create matches.
     * @return list of matches between the teams.
     * @throws InvalidNumberOfTeamsException
     */
    public ArrayList<Match> active() throws InvalidNumberOfTeamsException {
        this.matches = new ArrayList<>();
        ArrayList<Team> teams1 = this.season.getTeams();
        if (teams1.size()!=10)
            throw new InvalidNumberOfTeamsException(numberOfTeams, teams1.size());

        for (int i = 0; i < teams1.size(); i++) {
            System.out.println(teams1.get(i));
        }
        for (int i = 0; i < teams1.size(); i++){
            for (int j = i+1 ; j < teams1.size(); j++){
                matches.add(new Match(teams1.get(i),teams1.get(j), season));
            }
        }
        return matches;
    }
    /**
     * @param o
     * @return true if that's the same object or his class members equals else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePolicy that = (GamePolicy) o;
        return numberOfTeams == that.numberOfTeams;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfTeams);
    }
}

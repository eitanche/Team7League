package domain.LeagueComponents;

import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.TeamLoader;
import dataBase.Writers.IMatchWriter;
import dataBase.Writers.MatchWriter;
import domain.Policies.GamePolicy;
import domain.Subscriptions.Referee;

import java.util.ArrayList;
import java.util.Objects;

/**
 * represents season in League component
 */

public class Season {

    private String id;
    private String name;
    private GamePolicy gamePolicy;
    private ArrayList<Team> teams;
    private ArrayList<Referee> referees;
    private ArrayList<Match> matches;


    public Season(String id, String name, ArrayList<String> teamIDs, ArrayList<String> refereeIDs, String gamePolicyType) {
        this.id = id;
        this.name = name;
        this.teams = new ArrayList<>();
        for (String teamID: teamIDs) {
            this.teams.add(TeamLoader.getInstance().getTeam(teamID));
        }
        this.referees = new ArrayList<>();
        for (String refereeID: refereeIDs) {
            this.referees.add(RefereeLoader.getInstance().getReferee(refereeID));
        }
        if (gamePolicyType.equals("regular"))
            gamePolicy = new GamePolicy(this);
        this.matches = new ArrayList<>();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public GamePolicy getGamePolicy() {
        return gamePolicy;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setGamePolicy(GamePolicy gamePolicy) {
        this.gamePolicy = gamePolicy;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }


    /**
     * this function set matches to this season
     * @param m matches list
     * @param matchWriter writer to write into the DB
     */
    public void setMatches(ArrayList m, IMatchWriter matchWriter){
        matches = m;
        MatchWriter.getInstance().registerMatches(matches);
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * @param o
     * @return true if that's the same object or his class members equals else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return id.equals(season.id) && name.equals(season.name) && teams.equals(season.teams)  && matches.equals(season.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teams, referees, matches);
    }
}

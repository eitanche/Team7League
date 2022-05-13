package domain.LeagueComponents;

import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.TeamLoader;
import domain.Policies.GamePolicy;
import domain.Subscriptions.Referee;

import java.util.ArrayList;

public class Season {

    private String id;
    private String name;
    private GamePolicy gamePolicy;
    private ArrayList<Team> teams;
    private ArrayList<Referee> referees;
    private ArrayList<Match> matchs;


    public Season(String id, String name, String[] teamIDs, String[] refereeIDs, String gamePolicyType) {
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
            gamePolicy = new GamePolicy();
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

    public void setGamePolicy(GamePolicy gamePolicy) {
        this.gamePolicy = gamePolicy;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }

    public void setMatchs(ArrayList m){
        matchs = m;
        //send to eitan matchs...

    }
}

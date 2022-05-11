package domain;

import java.util.ArrayList;

public class Season {

    private GamePolicy gamePolicy;
    private Team[] teams;
    private ArrayList<Referee> referees;

    public Team[] getTeams() {
        return teams;
    }

    public GamePolicy getGamePolicy() {
        return gamePolicy;
    }

    public void setGamePolicy(GamePolicy gamePolicy) {
        this.gamePolicy = gamePolicy;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }
}

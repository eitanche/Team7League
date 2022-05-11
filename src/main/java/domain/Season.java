package domain;

public class Season {

    private GamePolicy gamePolicy;
    private Team[] teams;

    public Team[] getTeams() {
        return teams;
    }

    public GamePolicy getGamePolicy() {
        return gamePolicy;
    }

    public void setGamePolicy(GamePolicy gamePolicy) {
        this.gamePolicy = gamePolicy;
    }
}

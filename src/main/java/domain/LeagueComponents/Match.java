package domain.LeagueComponents;

import java.util.Objects;


/**
 * This class represents Match component
 */


public class Match {

    private Team home;
    private Team away;
    private String id;
    private Season season;

    public Match(Team home, Team away, Season s) {
        this.season = s;
        this.home = home;
        this.away = away;
        this.id = home.getId() +"_"+ away.getId() +"_"+ s.getId();
    }

    public Team getHomeTeam() {
        return home;
    }

    public Team getAwayTeam() {
        return away;
    }

    public String getId() {
        return id;
    }

    public Season getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return "Match{" +
                "home=" + home.toString() +
                ", away=" + away.toString() +
                ", season="+ season.toString() +"}";
    }

    /**
     * @param o
     * @return true if that's the same object or his class members equals else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(home, match.home) && Objects.equals(away, match.away) && Objects.equals(season, match.season);
    }

}

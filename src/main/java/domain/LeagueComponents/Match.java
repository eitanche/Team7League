package domain.LeagueComponents;

public class Match {

    private Team home;
    private Team away;
    private String id;
    private Season season;

    public Match(Team home, Team away, Season s) {
        this.home = home;
        this.away = away;
        this.id = home.getId() +"_"+ away.getId() +"_"+ s.getId();
    }
}

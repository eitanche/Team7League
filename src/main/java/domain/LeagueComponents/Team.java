package domain.LeagueComponents;

public class Team {
    private String id;
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
}

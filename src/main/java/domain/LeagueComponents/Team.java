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

    public String toString() {
        return "Team name: "+this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id) && name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

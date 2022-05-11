package domain.LeagueComponents;

import java.util.ArrayList;

public class League {

    private String id;
    private String name;
    private String[] seasonsIds;
    private Season currentSeason;
    private ArrayList<Season> seasons;

    public League(String id, String name, String[] seasonsIds) {
        this.id = id;
        this.name = name;
        this.seasonsIds = seasonsIds;
    }

    public String getName() {
        return name;
    }

    public String[] getSeasonsIds() {
        return seasonsIds;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }
}

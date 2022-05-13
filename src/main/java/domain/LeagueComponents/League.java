package domain.LeagueComponents;

import dataBase.Loaders.SeasonLoader;

import java.util.ArrayList;

public class League {

    private String id;
    private String name;
    private Season currentSeason;
    private ArrayList<Season> seasons;

    public League(String id, String name, String[] seasonsIds) {
        this.id = id;
        this.name = name;
        this.seasons = new ArrayList<>();
        for (String seasonID: seasonsIds) {
            seasons.add(SeasonLoader.getInstance().getSeason(seasonID));
        }
        if (this.seasons.size()>0) {
            currentSeason= this.seasons.get(0);
        }
    }

    public String getName() {
        return name;
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

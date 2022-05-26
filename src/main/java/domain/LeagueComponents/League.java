package domain.LeagueComponents;

import dataBase.Loaders.SeasonLoader;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents League component
 */


public class League {

    private String id;
    private String name;
    private Season currentSeason;
    private ArrayList<Season> seasons;

    public League(String id, String name, ArrayList<String> seasonsIds) {
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

    /**
     *
     * @param seasons set league seasons
     */
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

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @param o
     * @return true if that's the same object or his class members equals else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return id.equals(league.id) && name.equals(league.name) && currentSeason.equals(league.currentSeason) && seasons.equals(league.seasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, currentSeason, seasons);
    }
}

package domain.LeagueComponents;

import java.util.ArrayList;

public class League {

    private Season currentSeason;
    private ArrayList<Season> seasons;


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

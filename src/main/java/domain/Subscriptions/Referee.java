package domain.Subscriptions;

import domain.LeagueComponents.Season;

import java.util.ArrayList;

/**
 * This class represents a referee which will be assign to a seasons.
 */

public class Referee extends Subscription {

    private ArrayList<Season> season;

    public Referee(String id, String name){
        super(id, name);
        season = new ArrayList<>();
    }

    /**
     * add referee to param season.
     * @param season
     */
    public void setSeason(Season season) {
        this.season.add(season);
    }

    /**
     *
     * @return The list of seasons for which the referee is assigned.
     */
    public ArrayList<Season> getSeason() {
        return season;
    }


}

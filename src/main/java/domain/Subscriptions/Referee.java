package domain.Subscriptions;

import domain.LeagueComponents.Season;

import java.util.ArrayList;

public class Referee extends Subscription {

    private ArrayList<Season> season;

    public Referee(String id, String name){
        super(id, name);
        season = new ArrayList<>();
    }

    public void setSeason(Season season) {
        this.season.add(season);
    }

    public ArrayList<Season> getSeason() {
        return season;
    }


}

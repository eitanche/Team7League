package domain.Subscriptions;

import domain.LeagueComponents.Season;

import java.util.ArrayList;

public class Referee extends Subscription {

    private ArrayList<Season> season;

    public Referee(String id, String name){
        super(id, name);
    }

    public void setSeason(Season season) {
        this.season.add(season);
    }
}

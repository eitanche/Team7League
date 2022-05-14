package services;

import dataBase.DBInitiator;
import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.SubscriptionLoader;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Match;
import domain.Subscriptions.AssociationMember;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Exceptions.InvalidNumberOfTeamsException;
import Exceptions.NotAssociationMemberException;
import services.useCases.UC2GameScheduler;

import java.io.IOException;
import java.util.ArrayList;

public class UC2GameSchedulerTest {

    private UC2GameScheduler uc2GameScheduler;


    @Before
    public void before() throws NotAssociationMemberException, IOException {
        AssociationMember am = (AssociationMember) SubscriptionLoader.getInstance().authenticate("dm","11");
        uc2GameScheduler = new UC2GameScheduler(am);
        DBInitiator.initiateDB();
    }

    @Test
    public void assignAutoSeasonMatchesTest() throws InvalidNumberOfTeamsException { // check correction attribute of Season's matches
        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();

        Match match1 = new Match(leagues.get(0).getCurrentSeason().getTeams().get(2),leagues.get(0).getCurrentSeason().getTeams().get(4),leagues.get(0).getCurrentSeason());
        Match match2 = new Match(leagues.get(0).getCurrentSeason().getTeams().get(2),leagues.get(0).getCurrentSeason().getTeams().get(6),leagues.get(0).getCurrentSeason());
        Match match3 = new Match(leagues.get(0).getCurrentSeason().getTeams().get(4),leagues.get(0).getCurrentSeason().getTeams().get(7),leagues.get(0).getCurrentSeason());
        Match match4 = new Match(leagues.get(0).getCurrentSeason().getTeams().get(0),leagues.get(0).getCurrentSeason().getTeams().get(1),leagues.get(0).getCurrentSeason());
        ArrayList<Match> matchesToCheck = new ArrayList<>();
        matchesToCheck.add(match1);
        matchesToCheck.add(match2);
        matchesToCheck.add(match3);
        matchesToCheck.add(match4);

        this.uc2GameScheduler.assignAutoSeasonMatches(leagues.get(0));

        ArrayList<Match> matchesOfSeason = leagues.get(0).getCurrentSeason().getMatches();

        Assert.assertTrue(matchesOfSeason.containsAll(matchesToCheck));
    }

    @Test
    public void checkPolicy() throws InvalidNumberOfTeamsException { //check gamePolicy, each team have *one match* against other each team
        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();

        Match match1 = new Match(leagues.get(0).getCurrentSeason().getTeams().get(2),leagues.get(0).getCurrentSeason().getTeams().get(4),leagues.get(0).getCurrentSeason());
        Match match2 = new Match(leagues.get(0).getCurrentSeason().getTeams().get(4),leagues.get(0).getCurrentSeason().getTeams().get(2),leagues.get(0).getCurrentSeason());
        ArrayList<Match> matchesToCheck = new ArrayList<>();
        matchesToCheck.add(match1);
        matchesToCheck.add(match2);

        ArrayList<Match> matchesOfSeason = leagues.get(0).getCurrentSeason().getGamePolicy().active();

        Assert.assertFalse(matchesOfSeason.containsAll(matchesToCheck));

    }

    @Test
    public void checkEmptyLeague(){
        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();
        Assert.assertEquals(2, leagues.size());
    }
}

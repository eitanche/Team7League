package services;

import com.beust.ah.A;
import dataBase.DBInitiator;
import dataBase.Loaders.ILeagueLoader;
import dataBase.Loaders.ISubscriptionLoader;
import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.SubscriptionLoader;
import dataBase.Writers.IMatchWriter;
import dataBase.Writers.MatchWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.LeagueComponents.Team;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Referee;
import domain.Subscriptions.Subscription;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Exceptions.InvalidNumberOfTeamsException;
import Exceptions.NotAssociationMemberException;
import services.useCases.UC2GameScheduler;

import java.io.IOException;
import java.util.ArrayList;

public class UC2GameSchedulerTest {

    private LeagueLoader leagueLoader;
    private UC2GameScheduler uc2GameScheduler;
    private UC2GameScheduler uc2GameSchedulerStub;
    private LeagueLoaderStub leagueLoaderStub;
    private SubscriptionLoaderStub subscriptionLoaderStub;
    private AssociationMember associationMemberFromStub;
    private ArrayList<League> leaguesFromStub;
    private boolean leagueSizeFlag;
    private MatchWriter matchWriter;
    private MatchWriterStub matchWriterStub;

    @Before
    public void before() throws NotAssociationMemberException, IOException {
//        SubscriptionLoaderStub amStab = new SubscriptionLoaderStub();

        leagueLoaderStub = new LeagueLoaderStub();
        subscriptionLoaderStub = new SubscriptionLoaderStub();
        associationMemberFromStub = (AssociationMember) subscriptionLoaderStub.authenticate("d","d");
        leaguesFromStub = leagueLoaderStub.getLeagues();
        uc2GameSchedulerStub = new UC2GameScheduler(associationMemberFromStub, leagueLoaderStub);
        matchWriterStub = new MatchWriterStub();


        leagueLoader = LeagueLoader.getInstance();
        AssociationMember am = (AssociationMember) SubscriptionLoader.getInstance().authenticate("dm","11");
        uc2GameScheduler = new UC2GameScheduler(am, leagueLoader);
        matchWriter = MatchWriter.getInstance();
        DBInitiator.initiateDB();




    }

    @Test
    public void assignAutoSeasonMatchesTestIntegration() throws InvalidNumberOfTeamsException { // check correction attribute of Season's matches
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

        this.uc2GameScheduler.assignAutoSeasonMatches(leagues.get(0), matchWriter);

        ArrayList<Match> matchesOfSeason = leagues.get(0).getCurrentSeason().getMatches();

        Assert.assertTrue(matchesOfSeason.containsAll(matchesToCheck));
    }

    @Test
    public void checkPolicyIntegration() throws InvalidNumberOfTeamsException { //check gamePolicy, each team have *one match* against other each team
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
    public void checkEmptyLeagueIntegration(){
        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();
        Assert.assertEquals(2, leagues.size());
    }


    ///unit tests


    @Test
    public void assignAutoSeasonMatchesTest() throws InvalidNumberOfTeamsException {
        leagueSizeFlag = true;
        League league = leagueLoaderStub.getLeagues().get(0);
        uc2GameSchedulerStub.assignAutoSeasonMatches(league , matchWriterStub);
        Assert.assertNotNull(league.getCurrentSeason().getMatches());
    }

    @Test
    public void chooseLeagueToAssignAutoSeasonMatchesTest(){
        leagueSizeFlag = true;
        Assert.assertEquals(uc2GameSchedulerStub.chooseLeagueToAssignAutoSeasonMatches(leagueLoaderStub).size(),1);
    }

    @Test
    public void checkConditionsTest(){
        leagueSizeFlag = true;
        Assert.assertTrue(uc2GameSchedulerStub.checkConditions(leagueLoaderStub));
        leagueSizeFlag = false;
        Assert.assertFalse(uc2GameSchedulerStub.checkConditions(leagueLoaderStub));
    }

    class LeagueLoaderStub implements ILeagueLoader {

        public LeagueLoaderStub() { }

        public LeagueLoaderStub getInstance(){
            return new LeagueLoaderStub();
        }

        @Override
        public ArrayList<League> getLeagues() {
            ArrayList<League> league = new ArrayList<>();
            if (leagueSizeFlag){
                 league = createLeague();
            }
            return league;
        }

        public ArrayList<League> createLeague(){
            ArrayList<String> sid = new ArrayList<String>();
            sid.add("0");
            ArrayList<League> leagues = new ArrayList<>();
            leagues.add(new League("0", "Ligat Ha-al", sid));
            return leagues;
        }
    }

    class MatchWriterStub implements IMatchWriter{

        public MatchWriterStub() { }

        @Override
        public void registerMatches(ArrayList<Match> matches) {

        }
    }

    class SubscriptionLoaderStub implements ISubscriptionLoader {



        public SubscriptionLoaderStub() {
//            subscriptionFlag = true;
        }


        public SubscriptionLoaderStub getInstance() {

            return new SubscriptionLoaderStub();

        }

        @Override
        public Subscription authenticate(String userName, String hashedPassword) {
            return new AssociationMember("10","Deni Markovic");
        }

        @Override
        public boolean isUserExists(String userName) {
            return false;
        }
    }
}



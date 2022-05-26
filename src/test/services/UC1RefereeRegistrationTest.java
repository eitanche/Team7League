package services;

import Exceptions.SeasonNotExistsException;
import dataBase.DBInitiator;
import dataBase.Loaders.ILeagueLoader;
import dataBase.Loaders.IRefereeLoader;
import dataBase.Loaders.ISubscriptionLoader;
import dataBase.Loaders.SubscriptionLoader;
import dataBase.Writers.IRefereeWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Season;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Referee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import Exceptions.NotAssociationMemberException;
import org.junit.rules.ExpectedException;
import services.useCases.UC1RefereeRegistration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * class for test uc1.
 * data member:
 * uc1RefereeRegistration instance of UC1RefereeRegistration
 * exceptionRule instance of ExpectedException.
 */
public class UC1RefereeRegistrationTest {


    private UC1RefereeRegistration uc1RefereeRegistration;
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void before() throws NotAssociationMemberException, IOException {
        uc1RefereeRegistration = new UC1RefereeRegistration();
    }

    /**
     * Test Function which check all the leagues in data base.
     */
    @Test
    public void getAllLeaguesValidTest() {
        /**
         * craete new temporary stub league loder.
         */
        ILeagueLoader leagueLoaderStub = new ILeagueLoader() {
            @Override
            public ArrayList<League> getLeagues() {
                ArrayList<String> League1Seasons = new ArrayList<>();
                ArrayList<String> League2Seasons = new ArrayList<>();
                League1Seasons.add("0");
                League2Seasons.add("1");

                ArrayList<League> leagues = new ArrayList<>();
                leagues.add(new League("0","Ligat Ha-al",League1Seasons));
                leagues.add(new League("1","Super League",League2Seasons));
                return leagues;
            }
        };

        ArrayList<String> League1Seasons = new ArrayList<>();
        ArrayList<String> League2Seasons = new ArrayList<>();
        League1Seasons.add("0");
        League2Seasons.add("1");
        ArrayList<League> expectedLeagues = new ArrayList<>();
        expectedLeagues.add(new League("0","Ligat Ha-al",League1Seasons));
        expectedLeagues.add(new League("1","Super League",League2Seasons));
        ArrayList<League> actualLeagues = uc1RefereeRegistration.getAllLeagues(leagueLoaderStub);
        assertEquals(expectedLeagues, actualLeagues);
    }

    /**
     * Test Function which check if exception null pointer.
     */
    @Test
    public void getAllLeaguesNullTest() {
        exceptionRule.expect(NullPointerException.class);
        /**
         * Craete new temporary stub league loder.
         */
        ILeagueLoader emptyLeagueLoaderStub = new ILeagueLoader() {
            @Override
            public ArrayList<League> getLeagues() {
                return new ArrayList<League>();
            }
        };
        uc1RefereeRegistration.getAllLeagues(emptyLeagueLoaderStub);
    }

    /**
     * Test Function which check all the seasons in data base.
     */
    @Test
    public void getAllSeasonsOfLeagueValidTest() {
        ArrayList<String> teamIDS = new ArrayList<String>(
                Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
        ArrayList<Season> expectedSeasons = new ArrayList<>();
        expectedSeasons.add(new Season("0", "2019-2020",teamIDS, new ArrayList<String>() ,"regular"));

        ArrayList<String> League1Seasons = new ArrayList<>();
        League1Seasons.add("0");
        League league = new League("0","Ligat Ha-al",League1Seasons);
        ArrayList<Season> actualSeasons = uc1RefereeRegistration.getAllSeasonsOfLeague(league);
        assertEquals(expectedSeasons, actualSeasons);
    }

    /**
     * Test Function which check if exception null pointer - league without season.
     */
    @Test
    public void getAllSeasonsOfLeagueWithoutSeasonsTest() {
        exceptionRule.expect(NullPointerException.class);
        League league = new League("0", "Ligat Ha-al", new ArrayList<>());
        uc1RefereeRegistration.getAllSeasonsOfLeague(league);
    }

    /**
     * Test Function which check referees with new stub that add just two referees for the test.
     */
    @Test
    public void getAllRefereesValidTest() {
        IRefereeLoader refereeLoaderStub = new IRefereeLoader() {
            @Override
            public ArrayList<Referee> getAllReferees() {
                ArrayList<Referee> referees = new ArrayList<>();
                referees.add(new Referee("0","yosi"));
                referees.add(new Referee("1","dani"));
                return referees;
            }
        };
        ArrayList<Referee> actualReferees = uc1RefereeRegistration.getAllReferees(refereeLoaderStub);

        ArrayList<Referee> expectedReferees = new ArrayList<>();
        expectedReferees.add(new Referee("0","yosi"));
        expectedReferees.add(new Referee("1","dani"));

        assertEquals(expectedReferees, actualReferees);
    }

    /**
     * Test Function which check if exception null pointer - no referees.
     */
    @Test
    public void getAllRefereesExceptionTest() {
        exceptionRule.expect(NullPointerException.class);
        IRefereeLoader refereeLoaderStub = new IRefereeLoader() {
            @Override
            public ArrayList<Referee> getAllReferees() {
                return new ArrayList<>();
            }
        };
        uc1RefereeRegistration.getAllReferees(refereeLoaderStub);
    }

    /**
     * Test Function which check if assign referee successfully.
     * @throws SeasonNotExistsException
     */
    @Test
    public void assignRefereeToSeasonTest() throws SeasonNotExistsException {
        /**
         * Create new stub to not use the addRefereeToSeason function.
         */
        IRefereeWriter refereeWriterStub = new IRefereeWriter() {
            @Override
            public void addRefereeToSeason(Season season, Referee referee) throws SeasonNotExistsException {

            }
        };
        ArrayList<String> teamsId = new ArrayList<String>();
        for (int i=0; i<10; i++){
            teamsId.add(Integer.toString(i));
        }
        ArrayList<String> refereeId = new ArrayList<String>();
        for (int i=0; i<5; i++){
            refereeId.add(Integer.toString(i));
        }
        Season season = new Season("0", "0", teamsId, refereeId,"regular");
        Referee referee = new Referee("5", "sb");
        uc1RefereeRegistration.assignRefereeToSeason(season, referee, refereeWriterStub);
        assertTrue(season.getReferees().contains(referee));
        assertTrue(referee.getSeason().contains(season));
    }

    /**
     * check if the user try to apply the UC is the Association Member.
     */
    @Test
    public void isAssociationMemberTrueTest() {
        assertTrue(uc1RefereeRegistration.isAssociationMember(new AssociationMember("1","yosi")));
    }

    /**
     * check if the user(referee) try to apply the UC is the Association Member.
     */
    @Test
    public void isAssociationMemberFalseTest() {
        assertFalse(uc1RefereeRegistration.isAssociationMember(new Referee("1","yosi")));
    }

}


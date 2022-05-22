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

public class UC1RefereeRegistrationTest {

    private UC1RefereeRegistration uc1RefereeRegistration;
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void before() throws NotAssociationMemberException, IOException {
        uc1RefereeRegistration = new UC1RefereeRegistration();
    }

    @Test
    public void getAllLeaguesValidTest() {
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

    @Test
    public void getAllLeaguesNullTest() {
        exceptionRule.expect(NullPointerException.class);
        ILeagueLoader emptyLeagueLoaderStub = new ILeagueLoader() {
            @Override
            public ArrayList<League> getLeagues() {
                return new ArrayList<League>();
            }
        };
        uc1RefereeRegistration.getAllLeagues(emptyLeagueLoaderStub);
    }

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

    @Test
    public void getAllSeasonsOfLeagueWithoutSeasonsTest() {
        exceptionRule.expect(NullPointerException.class);
        League league = new League("0", "Ligat Ha-al", new ArrayList<>());
        uc1RefereeRegistration.getAllSeasonsOfLeague(league);
    }

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

    @Test
    public void assignRefereeToSeasonTest() throws SeasonNotExistsException {
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

    @Test
    public void isAssociationMemberTrueTest() {
        assertTrue(uc1RefereeRegistration.isAssociationMember(new AssociationMember("1","yosi")));
    }

    @Test
    public void isAssociationMemberFalseTest() {
        assertFalse(uc1RefereeRegistration.isAssociationMember(new Referee("1","yosi")));
    }

}


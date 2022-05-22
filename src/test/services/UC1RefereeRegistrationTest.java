package services;

import Exceptions.SeasonNotExistsException;
import dataBase.DBInitiator;
import dataBase.Loaders.ISubscriptionLoader;
import dataBase.Loaders.SubscriptionLoader;
import domain.LeagueComponents.Season;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Referee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Exceptions.NotAssociationMemberException;
import services.useCases.UC1RefereeRegistration;

import java.io.IOException;
import java.util.ArrayList;

public class UC1RefereeRegistrationTest {

    private UC1RefereeRegistration uc1RefereeRegistration;
    private ISubscriptionLoader userDB;
    @Before
    public void before() throws NotAssociationMemberException, IOException {
        AssociationMember am = (AssociationMember) SubscriptionLoader.getInstance().authenticate("dm","11");
        uc1RefereeRegistration = new UC1RefereeRegistration(am);
        DBInitiator.initiateDB();
    }
    @Test
    public void assignRefereeToSeasonTest() throws SeasonNotExistsException {
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
        uc1RefereeRegistration.assignRefereeToSeason(season, referee);
        Assert.assertTrue(season.getReferees().contains(referee));
        Assert.assertTrue(referee.getSeason().contains(season));
    }

}


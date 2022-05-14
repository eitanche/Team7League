import dataBase.DBInitiator;
import dataBase.Loaders.ILoader;
import dataBase.Loaders.SubscriptionLoader;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import services.useCases.NotAssociationMemberException;
import services.useCases.UC3LoginProcedure;

import java.io.IOException;


@RunWith(JUnit4.class)
public class UC3LoginProcedureTest {

    private ILoader userDB;
    private UC3LoginProcedure uc3LoginProcedure;

    @Before
    public void before() throws NotAssociationMemberException, IOException {
        userDB = SubscriptionLoader.getInstance();
        uc3LoginProcedure = new UC3LoginProcedure(userDB);
        DBInitiator.initiateDB();
    }

    @Test
    public void activeConnectTest() {//check user not exist or cant load correct from DB.
        AssociationMember am = new AssociationMember("10","Deni Markovic");
        Assert.assertEquals(uc3LoginProcedure.activeConnect("dm", "11"), am);
    }

    @Test
    public void PasswordIncorrectTest() {//if connect send we false the test good.
        Assert.assertNull((uc3LoginProcedure.activeConnect("dm","12")));
    }

}



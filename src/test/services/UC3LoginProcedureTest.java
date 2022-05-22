package services;

import dataBase.DBInitiator;
import dataBase.Loaders.ISubscriptionLoader;
import dataBase.Loaders.SubscriptionLoader;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Subscription;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import Exceptions.NotAssociationMemberException;
import services.useCases.UC3LoginProcedure;


import java.io.IOException;


@RunWith(JUnit4.class)
public class UC3LoginProcedureTest {

    private ISubscriptionLoader userDB;
    private ISubscriptionLoader userDBIntegrationTest;
    private UC3LoginProcedure uc3LoginProcedure;
    private UC3LoginProcedure uc3LoginProcedureIntegraion;
    private SubscriptionLoaderStub subscriptionstub;
    protected boolean subscriptionFlag;


    @Before
    public void before() throws NotAssociationMemberException, IOException {
        subscriptionstub = new SubscriptionLoaderStub();
        userDB = subscriptionstub.getInstance();
        uc3LoginProcedure = new UC3LoginProcedure(userDB);
        //integration
        userDBIntegrationTest = SubscriptionLoader.getInstance();
        uc3LoginProcedureIntegraion = new UC3LoginProcedure(userDBIntegrationTest);
        DBInitiator.initiateDB();
    }

    @Test
    public void activeConnectTest() {//check user not exist or cant load correct from DB.
        subscriptionFlag = true;
        AssociationMember am = new AssociationMember("10","Deni Markovic");
        Assert.assertEquals(uc3LoginProcedure.connect("dm", "11"), am);
    }

    @Test
    public void PasswordIncorrectTest() {//if connect send we false the test good.
        subscriptionFlag = false;
        Assert.assertNull((uc3LoginProcedure.connect("dm","12")));
    }

    @Test
    public void userNameIncorrectTest(){
        subscriptionFlag = false;
        Assert.assertNull((uc3LoginProcedure.connect("dm","12")));
    }

    @Test
    public void activeConnectIntegration(){
        AssociationMember am = new AssociationMember("10","Deni Markovic");
        Assert.assertEquals(uc3LoginProcedureIntegraion.connect("dm", "11"), am);
    }

    @Test
    public void PasswordIncorrectTestIntegration() {//if connect send we false the test good.
        Assert.assertNull((uc3LoginProcedureIntegraion.activeConnect("dm","12")));
    }

    @Test
    public void userNameIncorrectTestIntegration(){
        Assert.assertNull(uc3LoginProcedureIntegraion.connect("d2234522m", "11"));
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
            if (subscriptionFlag){
                return new AssociationMember("10","Deni Markovic");
            }
            else
                return null;
        }

        @Override
        public boolean isUserExists(String userName) {
            return false;
        }
    }
}



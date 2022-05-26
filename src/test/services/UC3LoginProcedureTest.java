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

/**
 * test class to test UseCase 3
 */
@RunWith(JUnit4.class)
public class UC3LoginProcedureTest {

    private ISubscriptionLoader userDB;
    private ISubscriptionLoader userDBIntegrationTest;
    private UC3LoginProcedure uc3LoginProcedure;
    private UC3LoginProcedure uc3LoginProcedureIntegraion;
    private SubscriptionLoaderStub subscriptionstub;
    protected boolean subscriptionFlag;

    /**
     * initiate DB before tests
     */
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

    /**
     * check user not exist or can't load correct from DB.
     */
    @Test
    public void activeConnectTest() {
        subscriptionFlag = true;
        AssociationMember am = new AssociationMember("10","Deni Markovic");
        Assert.assertEquals(uc3LoginProcedure.connect("dm", "11"), am);
    }

    /**
     * check login with wrong password
     */
    @Test
    public void PasswordIncorrectTest() {
        subscriptionFlag = false;
        Assert.assertNull((uc3LoginProcedure.connect("dm","12")));
    }

    /**
     * check login with wrong userName
     */
    @Test
    public void userNameIncorrectTest(){
        subscriptionFlag = false;
        Assert.assertNull((uc3LoginProcedure.connect("dm","12")));
    }

    /**
     * check if userName=dm is loaded correctly from DB
     */
    @Test
    public void activeConnectIntegration(){
        AssociationMember am = new AssociationMember("10","Deni Markovic");
        Assert.assertEquals(uc3LoginProcedureIntegraion.connect("dm", "11"), am);
    }

    /**
     * integration test to check password incorrect
     */
    @Test
    public void PasswordIncorrectTestIntegration() {
        Assert.assertNull((uc3LoginProcedureIntegraion.activeConnect("dm","12")));
    }

    /**
     * integration test to check userName incorrect
     */
    @Test
    public void userNameIncorrectTestIntegration(){
        Assert.assertNull(uc3LoginProcedureIntegraion.connect("d2234522m", "11"));
    }

    /**
     * stub class for tests
     */
    class SubscriptionLoaderStub implements ISubscriptionLoader {

        public SubscriptionLoaderStub() {
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



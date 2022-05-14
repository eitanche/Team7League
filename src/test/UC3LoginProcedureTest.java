//import dataBase.DBInitiator;
//import dataBase.Loaders.ILoader;
//import domain.Subscriptions.AssociationMember;
//import domain.Subscriptions.Subscription;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import services.useCases.UC3LoginProcedure;
//
//import java.io.IOException;
//
//
//@RunWith(JUnit4.class)
//public class UC3LoginProcedureTest {
//
//    private ILoader userDB;
//    private UC3LoginProcedure uc3LoginProcedure;
//    protected Boolean authenticateMethodVale;
//    protected Boolean isUserExistsMethodVale;
//
//    @Before
//    public void before() throws IOException {
//        uc3LoginProcedure = new UC3LoginProcedure(userDB);
//        authenticateMethodVale = true;
//        isUserExistsMethodVale = true;
//        DBInitiator.initiateDB();
//    }
//
//    @Test
//    public void connectTestShouldWork() {
//        Subscription new_user = new AssociationMember("10", "tal");
//        Assert.assertEquals(uc3LoginProcedure.connect());
//    }
//
//    @Test
//    public void PasswordIncorrectTest() {
//        authenticateMethodVale = false;
//        Assert.assertFalse(uc3LoginProcedure.connect());
//    }
//
//    @Test
//    public void userNotExistsTest() {
//        authenticateMethodVale = true;
//        isUserExistsMethodVale = false;
//        Assert.assertFalse(uc3LoginProcedure.connect("stam","stam"));
//    }
//
//    @After
//    public void endTest() {
//        userDB = null;
//        uc3LoginProcedure = null;
//        authenticateMethodVale = null;
//        isUserExistsMethodVale = null;
//    }
//
//
//    class DbHandlerStub implements IDbHandler {
//
//        @Override
//        public boolean authenticate(String userName, String hashedPassword) {
//            return authenticateMethodVale;
//        }
//
//        @Override
//        public boolean isUserExists(String userName) {
//            return isUserExistsMethodVale;
//        }
//    }
//}
//
//

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import services.UC3LoginProcedure;

@RunWith(JUnit4.class)
public class UC3LoginProcedureTest {

    private IDbHandler userDB;
    private UC3LoginProcedure uc3LoginProcedure;
    protected Boolean authenticateMethodVale;
    protected Boolean isUserExistsMethodVale;

    @Before
    public void before(){
        userDB = new DbHandlerStub();
        uc3LoginProcedure = new UC3LoginProcedure(userDB);
        authenticateMethodVale = true;
        isUserExistsMethodVale = true;
    }

    @Test
    public void connectTestShouldWork() {
        Assert.assertTrue(uc3LoginProcedure.connect("stam","stam"));
    }

    @Test
    public void PasswordIncorrectTest() {
        authenticateMethodVale = false;
        Assert.assertFalse(uc3LoginProcedure.connect("stam","stam"));
    }

    @Test
    public void userNotExistsTest() {
        authenticateMethodVale = true;
        isUserExistsMethodVale = false;
        Assert.assertFalse(uc3LoginProcedure.connect("stam","stam"));
    }

    @After
    public void endTest() {
        userDB = null;
        uc3LoginProcedure = null;
        authenticateMethodVale = null;
        isUserExistsMethodVale = null;
    }


    class DbHandlerStub implements IDbHandler {

        @Override
        public boolean authenticate(String userName, String hashedPassword) {
            return authenticateMethodVale;
        }

        @Override
        public boolean isUserExists(String userName) {
            return isUserExistsMethodVale;
        }
    }
}



package services.useCases;


import dataBase.Loaders.ILoader;
import domain.Subscriptions.Subscription;

public class UC3LoginProcedure {

    private ILoader userDb;

    public UC3LoginProcedure(ILoader userdb) {
        this.userDb = userdb;
    }

    public boolean connect(String userName, String password){
        if (checkUserNameExist(userName)){
            String hashedPassword = makeHash(password);
            if (isCorrect(userName,hashedPassword)){
                return true;
            }
            else{
                return false;
            }
        }
        else
        {
            return false;
        }

    }

    private boolean isCorrect(String userName, String hashedPassword) {
        return userDb.authenticate(userName,hashedPassword);
    }

    private boolean checkUserNameExist(String userName) {
        return userDb.isUserExists(userName);
    }

    //need to add hash function
    private String makeHash(String password){

        return password;
    }


}

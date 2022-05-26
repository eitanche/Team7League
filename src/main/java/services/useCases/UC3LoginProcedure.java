package services.useCases;


import dataBase.Loaders.ISubscriptionLoader;
import domain.Subscriptions.Subscription;

import java.util.Scanner;

/**
 * operate class to Log in Procedure.
 * data member:
 * userDb - ISubscriptionLoader object that linked the data base to service leyer and pull the relevant data from data base.
 */
public class UC3LoginProcedure {

    private ISubscriptionLoader userDb;

    public UC3LoginProcedure(ISubscriptionLoader userdb) {
        this.userDb = userdb;
    }

    /**
     * This fuction active the user.
     * @param userName
     * @param password
     * @return The subscription according the type of user.
     */
    public Subscription connect(String userName, String password) {
        return activeConnect(userName, password);
    }

    /**
     * This fuction active the user and check if the user name and password are correct.
     * @param userName
     * @param password
     * @return The subscription according the type of user.
     */
    public Subscription activeConnect(String userName, String password){
        Subscription logedin_user = userDb.authenticate(userName, password);
        if (logedin_user == null) {
            System.out.println("One Or More Details Are Wrong");
            return null;
        }
        System.out.println("Connect Successfully");
        return logedin_user;
    }
}



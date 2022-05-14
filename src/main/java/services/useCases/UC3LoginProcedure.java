package services.useCases;


import dataBase.Loaders.ILoader;
import dataBase.Loaders.SubscriptionLoader;
import domain.Subscriptions.Subscription;

import java.util.Scanner;

public class UC3LoginProcedure {

    private ILoader userDb;

    public UC3LoginProcedure(ILoader userdb) {
        this.userDb = userdb;
    }

    public Subscription connect() {
        Scanner inpu = new Scanner(System.in);
        String input;
        System.out.println("Please Enter User-Name:\n");
        input = inpu.nextLine();
        String userName = input;
        System.out.println("Please Enter Password:\n");
        input = inpu.nextLine();
        String password = input;
        return activeConnect(userName, password);
    }
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

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

        Subscription logedin_user = userDb.authenticate(userName, password);
        if (logedin_user == null) {
            return null;
        }
        return logedin_user;
    }




}

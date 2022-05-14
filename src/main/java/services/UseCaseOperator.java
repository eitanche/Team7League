package services;

import Exceptions.LeagueManagementException;
import dataBase.DBInitiator;
import dataBase.Loaders.SubscriptionLoader;
import domain.Subscriptions.Subscription;
import services.useCases.UC1RefereeRegistration;
import services.useCases.UC2GameScheduler;
import services.useCases.UC3LoginProcedure;
import Exceptions.NotAssociationMemberException;

import java.io.IOException;
import java.util.Scanner;

public class UseCaseOperator {

    private static Subscription logedIn = null;

    public static void main(String[] args) throws IOException {
        DBInitiator.initiateDB();
        System.out.println("Use-Cases:");
        System.out.println("*Please select one option*");
        String input = "1";

        while(!input.equals("4")) {
            System.out.println("1. Use Case -1- Referee Registration\n2. Use Case -2- Game Scheduler\n3. Use Case -3- Login Procedure\n4. Exit\n");
            Scanner inpu = new Scanner(System.in);
            input = inpu.nextLine();
            switch (input){
                case "1":
                    if (logedIn==null){
                        System.out.println("Please log-in to Association Member account first.");
                        break;
                    }
                    try{
                        UC1RefereeRegistration uc1 = new UC1RefereeRegistration(logedIn);
                        uc1.RefereeRegistration();
                    }
                    catch (LeagueManagementException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    break;
                case "2":
                    if (logedIn==null){
                        System.out.println("Please log-in to Association Member account first.");
                        break;
                    }
                    try {
                        UC2GameScheduler uc2 = new UC2GameScheduler(logedIn);
                        uc2.GameScheduler();
                    }
                    catch (LeagueManagementException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case "3":
                    UC3LoginProcedure uc3 = new UC3LoginProcedure(SubscriptionLoader.getInstance());
                    logedIn = uc3.connect();
                    break;
            }
        }
    }
}

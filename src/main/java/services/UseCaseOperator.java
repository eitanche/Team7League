package services;

import dataBase.Loaders.ILoader;
import dataBase.Loaders.SubscriptionLoader;
import domain.Subscriptions.AssociationMember;
import services.useCases.UC1RefereeRegistration;
import services.useCases.UC2GameScheduler;
import services.useCases.UC3LoginProcedure;

import javax.swing.text.StyledEditorKit;
import java.util.Scanner;

public class UseCaseOperator {
    private static SubscriptionLoader subLoader;

    public static void main(String[] args) {
        System.out.println("Use-Cases:");
        System.out.println("*Please select one option*");
        String input = "1";
        AssociationMember am = null;
        String userName;
        String password;

        while(!input.equals("4")) {
            System.out.println("1. Use Case -1- Referee Registration\n2. Use Case -2- Game Scheduler\n3. Use Case -3- Login Procedure\n4. Exit\n");
            Scanner inpu = new Scanner(System.in);
            System.out.println("Enter Your Choice:\n");
            input = inpu.nextLine();
            switch (input){
                case "1":
                    if (am==null){
                        System.out.println("Please log-in to Association Member account first.");
                        break;
                    }
                    UC1RefereeRegistration uc1 = new UC1RefereeRegistration(subLoader);
                    uc1.RefereeRegistration();
                    break;
                case "2":
                    if (am==null){
                        System.out.println("Please log-in to Association Member account first.");
                        break;
                    }
                    UC2GameScheduler uc2 = new UC2GameScheduler(subLoader);
                    uc2.GameScheduler();
                    break;
                case "3":
                    System.out.println("Please Enter User-Name:\n");
                    input = inpu.nextLine();
                    userName = input;
                    System.out.println("Please Enter Password:\n");
                    input = inpu.nextLine();
                    password = input;
                    SubscriptionLoader subLoader =  new SubscriptionLoader();
                    subLoader.authenticate(userName, password);
                    UC3LoginProcedure uc3 = new UC3LoginProcedure(subLoader);
                    uc3.connect(userName, password);
                    break;
            }
        }
    }
}

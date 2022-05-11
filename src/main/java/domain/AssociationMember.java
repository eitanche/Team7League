package domain;

import dataBase.IDbHandler;

import java.util.Scanner;

public class AssociationMember extends Subscription {

    private IDbHandler userDb;
    private SystemManagment sm;
    private League[] leagues; // יש כמה ליגות פעילות בו-זמנית ?


    public void assignAutoSeasonMatches() {

        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
        choosedLeague.getCurrentSeason().getGamePolicy().active();

    }

    private League chooseLeagueToAssignAutoSeasonMatches() {

        System.out.println("Please Choose League");
        for (int i = 0; i <  leagues.length; i++) {
            System.out.println(leagues[i]);
        }
        Scanner scanner = new Scanner(System.in);
        int inputLeague = scanner.nextInt();

        return leagues[inputLeague];
    }

    public void assignReferees() {
        League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
    }
}

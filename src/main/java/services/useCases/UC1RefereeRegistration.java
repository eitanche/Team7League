package services.useCases;

import dataBase.Loaders.ILoader;
import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.SeasonLoader;
import dataBase.Writers.RefereeWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Season;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Referee;
import domain.Subscriptions.Subscription;

import java.util.ArrayList;
import java.util.Scanner;

public class UC1RefereeRegistration {

    private AssociationMember am;

    public UC1RefereeRegistration(Subscription am) throws NotAssociationMemberException {
        if (am instanceof AssociationMember)
            this.am = (AssociationMember)am;
        else
            throw new NotAssociationMemberException();
    }

    public void RefereeRegistration() {
        if (!checkConditions())
            return;
        try{
            League choosedLeague = chooseLeagueToAssignAutoSeasonMatches();
            Season choosedSeason = chooseSeasonToAssignReferees(choosedLeague);
            Referee choosedReferee = chooseRefereeToAssign();

            assignRefereeToSeason(choosedSeason, choosedReferee);

            System.out.println("Click 1 to assign more referee to the season\nClick 2 to exit");
            Scanner scanner = new Scanner(System.in);
            int result = scanner.nextInt();
            while (result == 1){
                choosedReferee = chooseRefereeToAssign();
                assignRefereeToSeason(choosedSeason, choosedReferee);
                System.out.println("Click 1 for assign more referee to the season\nClick 2 for exit");
                result = scanner.nextInt();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("The Use Case Finished");
    }

    public void assignRefereeToSeason(Season choosedSeason, Referee choosedReferee) {
        RefereeWriter.getInstance().addRefereeToSeason(choosedSeason, choosedReferee);
        choosedReferee.setSeason(choosedSeason);
        choosedSeason.getReferees().add(choosedReferee);
    }

    public Referee chooseRefereeToAssign() {
        ArrayList<Referee> referees =  RefereeLoader.getInstance().getAllReferees();
        if (referees==null)
            throw new NullPointerException();
        return referees.get(activeChooseRefereeToAssign(referees));
    }
    public int activeChooseRefereeToAssign(ArrayList<Referee> refList){
        System.out.println("Please Choose Referee To Assign");
        for (int i = 0; i <  refList.size(); i++) {
            System.out.println((i+1) + ". " +refList.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputReferee = scanner.nextInt();
        return inputReferee-1;
    }

    private League chooseLeagueToAssignAutoSeasonMatches() {

        ArrayList<League> leagues = LeagueLoader.getInstance().getLeagues();
        if (leagues==null)
            throw new NullPointerException();
        System.out.println("Please Choose League");
        for (int i = 0; i <  leagues.size(); i++) {
            System.out.println((i+1) + ". " + leagues.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputLeague = scanner.nextInt();

        return leagues.get(inputLeague - 1);
    }


    private Season chooseSeasonToAssignReferees(League league) {

        ArrayList<Season> seasons =  league.getSeasons();
        if (seasons==null)
            throw new NullPointerException();
        System.out.println("Please Choose Season To Assign Referees");
        for (int i = 0; i <  seasons.size(); i++) {
            System.out.println((i+1) + ". " + seasons.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int inputSeason = scanner.nextInt();

        return seasons.get(inputSeason - 1);
    }

    private boolean checkConditions() {

        if (RefereeLoader.getInstance().getAllReferees().isEmpty()) {
            System.out.println("There Are No Referees In The System");
            return false;
        }
        if (LeagueLoader.getInstance().getLeagues().isEmpty()) {
            System.out.println("There Are No Leagues In The System");
            return false;
        }
        if (SeasonLoader.getInstance().getSeason("0") == null) {
            System.out.println("There Are No Seasons In The System");
            return false;
        }

        return true;
    }


}

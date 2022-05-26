package services.useCases;

import Exceptions.NotAssociationMemberException;
import Exceptions.SeasonNotExistsException;
import dataBase.Loaders.*;
import dataBase.Writers.IRefereeWriter;
import dataBase.Writers.RefereeWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Season;
import domain.Subscriptions.AssociationMember;
import domain.Subscriptions.Referee;
import domain.Subscriptions.Subscription;

import java.util.ArrayList;

/**
 * operate class to Assign Referees.
 */
public class UC1RefereeRegistration {
    /**
     * get all the leagues from the data base.
     * @param leagueLoader - ILeaueLoader object that linked the data base to service leyer and pull the relevant data from data base.
     * @return list of the leagues in the data base.
     */
    public ArrayList<League> getAllLeagues(ILeagueLoader leagueLoader) {
        ArrayList<League> allLeagues = leagueLoader.getLeagues();
        if (allLeagues==null || allLeagues.size()==0)
            throw new NullPointerException();
        return allLeagues;
    }

    /**
     * get the season of the specific league from the data base.
     * @param league - specific league.
     * @return Season object that linked by specific league.
     */
    public ArrayList<Season> getAllSeasonsOfLeague(League league) {
        ArrayList<Season> seasons =  league.getSeasons();
        if (seasons==null || seasons.size()==0)
            throw new NullPointerException();
        return seasons;
    }

    /**
     * get all referees of the data base.
     * @param refereeLoader - IRefereeLoader object that linked the data base to service leyer and pull the relevant data from data base.
     * @return list of the referees in the data base.
     */
    public ArrayList<Referee> getAllReferees(IRefereeLoader refereeLoader) {
        ArrayList<Referee> referees =  refereeLoader.getAllReferees();
        if (referees==null || referees.size()==0)
            throw new NullPointerException();
        return referees;
    }

    /**
     * A function which Scheduling referees for the season according to the UC.
     * @param choosedSeason - The season chosen by the association member.
     * @param choosedReferee - The referee chosen by the association member.
     * @param refereeWriter - IRefereeWriter object that linked the data base to service leyer and write the referee to data base.
     * @throws SeasonNotExistsException
     */
    public void assignRefereeToSeason(Season choosedSeason, Referee choosedReferee, IRefereeWriter refereeWriter) throws SeasonNotExistsException {
        refereeWriter.addRefereeToSeason(choosedSeason, choosedReferee);
        choosedReferee.setSeason(choosedSeason);
        choosedSeason.getReferees().add(choosedReferee);
    }

    /**
     * check if the user is association member.
     * @param subscription
     * @return
     */
    public boolean isAssociationMember(Subscription subscription) {
        return (subscription instanceof AssociationMember);
    }

}

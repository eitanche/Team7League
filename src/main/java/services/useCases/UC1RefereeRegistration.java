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

public class UC1RefereeRegistration {
    public ArrayList<League> getAllLeagues(ILeagueLoader leagueLoader) {
        ArrayList<League> allLeagues = leagueLoader.getLeagues();
        if (allLeagues==null || allLeagues.size()==0)
            throw new NullPointerException();
        return allLeagues;
    }

    public ArrayList<Season> getAllSeasonsOfLeague(League league) {
        ArrayList<Season> seasons =  league.getSeasons();
        if (seasons==null || seasons.size()==0)
            throw new NullPointerException();
        return seasons;
    }

    public ArrayList<Referee> getAllReferees(IRefereeLoader refereeLoader) {
        ArrayList<Referee> referees =  refereeLoader.getAllReferees();
        if (referees==null || referees.size()==0)
            throw new NullPointerException();
        return referees;
    }

    public void assignRefereeToSeason(Season choosedSeason, Referee choosedReferee, IRefereeWriter refereeWriter) throws SeasonNotExistsException {
        refereeWriter.addRefereeToSeason(choosedSeason, choosedReferee);
        choosedReferee.setSeason(choosedSeason);
        choosedSeason.getReferees().add(choosedReferee);
    }

}

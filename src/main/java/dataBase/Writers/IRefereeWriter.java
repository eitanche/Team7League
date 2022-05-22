package dataBase.Writers;

import Exceptions.SeasonNotExistsException;
import domain.LeagueComponents.Season;
import domain.Subscriptions.Referee;

public interface IRefereeWriter {
    void addRefereeToSeason(Season season, Referee referee) throws SeasonNotExistsException;
}

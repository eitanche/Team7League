package dataBase.Exceptions;

import domain.LeagueComponents.Season;

public class SeasonNotExistsException extends Exception{
    public SeasonNotExistsException(Season season) {
        super("The following season doesn't exist in the DB: "+season.toString());
    }
}

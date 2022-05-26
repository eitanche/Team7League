package dataBase.Writers;

import domain.LeagueComponents.Match;

import java.util.ArrayList;

/**
 * interface for MatchWriter
 */
public interface IMatchWriter {

    public void registerMatches(ArrayList<Match> matches);
}

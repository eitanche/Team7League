package dataBase.Writers;

import domain.LeagueComponents.Match;

import java.util.ArrayList;

public interface IMatchWriter {

    public void registerMatches(ArrayList<Match> matches);
}

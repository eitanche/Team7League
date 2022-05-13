package domain.Subscriptions;

import dataBase.Loaders.LeagueLoader;
import dataBase.Loaders.RefereeLoader;
import dataBase.Loaders.SeasonLoader;
import dataBase.Writers.RefereeWriter;
import domain.LeagueComponents.League;
import domain.LeagueComponents.Match;
import domain.LeagueComponents.Season;
import domain.SystemManagment;

import java.util.ArrayList;
import java.util.Scanner;

public class AssociationMember extends Subscription {
    public AssociationMember(String id, String name) {
        super(id, name);
    }
}

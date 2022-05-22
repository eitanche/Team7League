package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.LeagueComponents.Team;
import org.bson.Document;

public class TeamLoader extends ADatabaseHandler implements ITeamLoader{
    private static TeamLoader instance=null;

    private TeamLoader() {
        super();
    }

    public static TeamLoader getInstance() {
        if (instance==null)
            instance = new TeamLoader();
        return instance;
    }

    public Team getTeam(String teamID) {
        Document desiredTeam = new Document();
        desiredTeam.put("_id", teamID);
        desiredTeam = database.getCollection("Teams").find(desiredTeam).first();
        if (desiredTeam==null)
            return null;
        return new Team((String)desiredTeam.get("_id"), (String)desiredTeam.get("name"));
    }

}

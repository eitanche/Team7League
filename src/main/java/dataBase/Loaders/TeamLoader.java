package dataBase.Loaders;

import dataBase.ADatabaseHandler;
import domain.LeagueComponents.Team;
import org.bson.Document;


/**
 * Singelton class for load leagues from DB
 */
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
    /**
     * load team from DB with teamID
     * @param teamID - id of desired team
     * @return team object
     */
    public Team getTeam(String teamID) {
        Document desiredTeam = new Document();
        desiredTeam.put("_id", teamID);
        desiredTeam = database.getCollection("Teams").find(desiredTeam).first();
        if (desiredTeam==null)
            return null;
        return new Team((String)desiredTeam.get("_id"), (String)desiredTeam.get("name"));
    }

}

package dataBase.Loaders;

import domain.Subscriptions.*;

import java.util.Locale;


/**
 * factory class in order to create instances
 */

public class SubscriptionFactory {
    /**
     * static function to create instances with parameters according to DB keys.
     * @param id - id of the desired object
     * @param name - name of the desired object
     * @param role - role of the desired object
     * @return instance of inherited Subscription class
     */

    public static Subscription getSubscriptionObject(String id, String name, String role){
        if ((role.toLowerCase(Locale.ENGLISH)).equals("associationmember")) {
            return new AssociationMember(id, name);
        }
        else if ((role.toLowerCase(Locale.ENGLISH)).equals("player")){
            return new Player(id, name);
        }
        else if ((role.toLowerCase(Locale.ENGLISH)).equals("referee")){
            return new Referee(id, name);
        }
        else if ((role.toLowerCase(Locale.ENGLISH)).equals("systemmanager")){
            return new SystemManager(id, name);
        }
        return null;
    }


}

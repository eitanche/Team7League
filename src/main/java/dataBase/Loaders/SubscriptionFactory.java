package dataBase.Loaders;

import domain.Subscriptions.*;

import java.util.Locale;

public class SubscriptionFactory {
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

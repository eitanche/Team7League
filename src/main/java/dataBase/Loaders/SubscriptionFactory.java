package dataBase.Loaders;

import domain.Subscriptions.*;

import java.util.Locale;

public class SubscriptionFactory {
    public static Subscription getSubscriptionObject(String name, String role){
        if ((role.toLowerCase(Locale.ENGLISH)).equals("associationmember")) {
            return new AssociationMember(name);
        }
        else if ((role.toLowerCase(Locale.ENGLISH)).equals("player")){
            return new Player(name);
        }
        else if ((role.toLowerCase(Locale.ENGLISH)).equals("referee")){
            return new Referee(name);
        }
        else if ((role.toLowerCase(Locale.ENGLISH)).equals("systemmanager")){
            return new SystemManager(name);
        }
        return null;
    }


}

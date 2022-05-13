package services.useCases;

import dataBase.Loaders.ILoader;
import domain.Subscriptions.AssociationMember;

public class UC1RefereeRegistration {

    private ILoader iDbHandler;
    private AssociationMember am;


    public UC1RefereeRegistration(ILoader iDbHandler) {
        this.iDbHandler = iDbHandler;
        this.am = ; // need to create from the dbHandler
    }

    public void RefereeRegistration(){
        if (!checkConditions())
            return;

        am.assignReferees(); // lahaz al rishum shoftim

    }

    private boolean checkConditions() {
        return true;
    }


}

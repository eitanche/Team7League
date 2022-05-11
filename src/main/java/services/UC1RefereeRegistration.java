package services;

import dataBase.IDbHandler;
import domain.AssociationMember;

public class UC1RefereeRegistration {

    private IDbHandler iDbHandler;
    private AssociationMember am;

    public UC1RefereeRegistration(IDbHandler iDbHandler) {
        this.iDbHandler = iDbHandler;
        this.am = new AssociationMember(); // need to create from the dbHandler
    }

    public void RefereeRegistration(){
        if (!checkConditions())
            return;

        am.assignReferees(); // lahaz al rishum shoftim

    }

}

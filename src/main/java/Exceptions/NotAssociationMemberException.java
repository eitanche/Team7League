package Exceptions;

public class NotAssociationMemberException extends LeagueManagementException {
    public NotAssociationMemberException() {
        super("This action is restricted to AssociationMembers only");
    }
}
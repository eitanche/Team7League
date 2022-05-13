package services.useCases;

public class NotAssociationMemberException extends Exception {
    public NotAssociationMemberException() {
        super("This action is restricted to AssociationMembers only")
    }
}
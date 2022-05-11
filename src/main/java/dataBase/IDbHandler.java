package dataBase;

public interface IDbHandler {
    boolean authenticate(String userName, String hashedPassword);

    boolean isUserExists(String userName);
}

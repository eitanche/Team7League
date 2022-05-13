package services.useCases;

public class InvalidNumberOfTeamsException extends Exception {
    public InvalidNumberOfTeamsException(int expectedNumberOfTeams, int actualNumberOfTeams) {
        super(String.format("Invalid number of teams in season. Expected: %d, actual: %d", expectedNumberOfTeams, actualNumberOfTeams));
    }
}

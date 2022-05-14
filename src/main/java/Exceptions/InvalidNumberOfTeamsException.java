package Exceptions;

public class InvalidNumberOfTeamsException extends LeagueManagementException {
    public InvalidNumberOfTeamsException(int expectedNumberOfTeams, int actualNumberOfTeams) {
        super(String.format("Invalid number of teams in season. Expected: %d, actual: %d", expectedNumberOfTeams, actualNumberOfTeams));
    }
}

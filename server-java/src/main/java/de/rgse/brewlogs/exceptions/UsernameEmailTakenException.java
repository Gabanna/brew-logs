package de.rgse.brewlogs.exceptions;

public class UsernameEmailTakenException extends BrewLogsException {

    private final String username;
    private final String email;

    public UsernameEmailTakenException(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public String getMessage() {
        return String.format("username or email are already taken: %s ; %s", username, email);
    }
}

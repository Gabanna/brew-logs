package de.rgse.brewlogs.exceptions;

public class UserExistsException extends BrewLogsException {

    private String username;

    public UserExistsException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getMessage() {
        return String.format("the user %s is already registered", username);
    }
}

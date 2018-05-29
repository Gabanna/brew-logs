package de.rgse.brewlogs.env;

public enum SystemEnv {

    BL_API_KEY(true), BL_TOKEN_EXPIRATION(true);

    private String key;
    private boolean mandatory;

    private SystemEnv(String value, boolean mandatory) {
        this.key = value;
        this.mandatory = mandatory;
    }

    private SystemEnv(boolean mandatory) {
        this.key = name();
        this.mandatory = mandatory;
    }

    public String getKey() {
        return key;
    }

    public boolean isMandatory() {
        return mandatory;
    }
}

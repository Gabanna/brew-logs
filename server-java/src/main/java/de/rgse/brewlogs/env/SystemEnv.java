package de.rgse.brewlogs.env;

public enum SystemEnv {

    BL_API_KEY, NULL;

    private String key;

    private SystemEnv(String value) {
        this.key = value;
    }

    private SystemEnv() {
        this.key = name();
    }

    public String getKey() {
        return key;
    }
}

package de.rgse.brewlogs.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ErrorVo {

    @JsonIgnore
    private Throwable throwable;

    @JsonProperty
    private String uuid;

    public ErrorVo(Throwable throwable) {
        this.throwable = throwable;
        this.uuid = UUID.randomUUID().toString();
    }

    @JsonProperty
    public String getMessage() {
        return throwable.getMessage();
    }

    @JsonIgnore
    public Throwable getThrowable() {
        return throwable;
    }

    @JsonIgnore
    public String getLogMessage() {
        return String.format("[error %s] %s", uuid, throwable.getMessage());
    }
}

package de.rgse.brewlogs.exceptions;

import com.sun.jndi.cosnaming.ExceptionMapper;

public class BrewLogsException extends Exception {

    public BrewLogsException(){}

    public BrewLogsException(String message){
        super(message);
    }

    public BrewLogsException(Throwable throwable){
        super(throwable);
    }
}

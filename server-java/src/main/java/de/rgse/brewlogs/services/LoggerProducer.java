package de.rgse.brewlogs.services;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerProducer {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return LogManager.getLogManager().getLogger(getTarget(injectionPoint));
    }

    private String getTarget(InjectionPoint injectionPoint) {
        String target = "";

        if(injectionPoint.getBean() != null) {
            target = injectionPoint.getBean().getName();

        } else if(injectionPoint.getMember() != null) {
            target = injectionPoint.getMember().getName();

        }

        return target;
    }
}

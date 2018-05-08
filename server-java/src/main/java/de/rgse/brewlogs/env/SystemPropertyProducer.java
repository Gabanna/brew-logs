package de.rgse.brewlogs.env;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

public class SystemPropertyProducer {

    @Produces
    @SystemProperty(SystemEnv.NULL)
    public String produceProperty(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        SystemProperty systemProperty = annotated.getAnnotation(SystemProperty.class);
        String key = systemProperty.value().getKey();
        return readValue(key);
    }

    private String readValue(String key) {
        String value = System.getProperty(key);
        if(value == null) {
            value = System.getenv(key);
        }
        return value;
    }
}

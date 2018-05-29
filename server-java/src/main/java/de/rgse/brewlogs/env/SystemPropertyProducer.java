package de.rgse.brewlogs.env;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

public class SystemPropertyProducer {

    @Produces
    @SystemProperty(SystemEnv.BL_API_KEY)
    public String produceProperty(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        SystemProperty systemProperty = annotated.getAnnotation(SystemProperty.class);
        SystemEnv key = systemProperty.value();
        return readValue(key);
    }

    String readValue(SystemEnv systemEnv) {
        String value = System.getProperty(systemEnv.getKey());
        if(value == null) {
            value = System.getenv(systemEnv.getKey());
        }
        return value;
    }
}

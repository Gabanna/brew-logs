package de.rgse.brewlogs.env;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Startup
@Singleton
public class EnvironmentValidator {

    @PostConstruct
    public void validateEnvironment() {
        Logger logger = LogManager.getLogManager().getLogger(getClass().getSimpleName());
        SystemPropertyProducer systemPropertyProducer = new SystemPropertyProducer();

        Set<SystemEnv> missingProperties = new HashSet<>();
        Map<SystemEnv, String> environment = new HashMap<>();

        for (SystemEnv systemEnv : SystemEnv.values()) {
            String value = systemPropertyProducer.readValue(systemEnv);
            environment.put(systemEnv, value);
            if(null == value && systemEnv.isMandatory()) {
                missingProperties.add(systemEnv);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("system environment\n\n");
        for (Map.Entry<SystemEnv, String> systemEnvStringEntry : environment.entrySet()) {
            stringBuilder.append("\t").append(systemEnvStringEntry.getKey()).append(" : ").append(systemEnvStringEntry.getValue()).append("\n");
        }
        stringBuilder.append("\n");

        logger.info(stringBuilder.toString());

        if(!missingProperties.isEmpty()) {
            throw new RuntimeException("the following properties are mandatory but missing: " + missingProperties.toString());
        }
    }
}

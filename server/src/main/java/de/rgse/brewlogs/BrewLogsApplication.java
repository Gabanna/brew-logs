package de.rgse.brewlogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.rgse.brewlogs.service.BrewLogService;

@SpringBootApplication
public class BrewLogsApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(BrewLogsApplication.class, args);
        BrewLogService bls = context.getBean(BrewLogService.class);
        bls.startNewBrewLog("bwxt109");
    }

}

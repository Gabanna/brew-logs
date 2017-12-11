package de.rgse.brewlogs;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BrewLogsApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(BrewLogsApplication.class, args);
        RuntimeService runtimeService = context.getBean(RuntimeService.class);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_Brauprozess", "123");
    }

}

package de.rgse.brewlogs.process;

import org.camunda.bpm.engine.ProcessEngine;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Singleton
@Startup
public class ProcessEngineConfiguration {

    @PersistenceUnit(unitName = "primary")
    private EntityManagerFactory entityManagerFactory;

    private ProcessEngine processEngine;

    @PostConstruct
    private void onStartup() {
        org.camunda.bpm.engine.ProcessEngineConfiguration engineConfiguration = org.camunda.bpm.engine.ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setProcessEngineName("brewLogsProcessEngine")
                .setJobExecutorActivate(true)
                .setDatabaseSchemaUpdate(org.camunda.bpm.engine.ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJpaEntityManagerFactory(entityManagerFactory)
                .setCreateIncidentOnFailedJobEnabled(true)
                .setTransactionsExternallyManaged(true);
        engineConfiguration.setHistory(org.camunda.bpm.engine.ProcessEngineConfiguration.HISTORY_FULL);
        engineConfiguration.setDataSourceJndiName("java:jboss/datasources/ExampleDS");
        processEngine = engineConfiguration.buildProcessEngine();
    }

    @Produces
    @BrewLog
    public ProcessEngine createEngine() {
        return processEngine;
    }
}
package de.rgse.brewlogs.process;

import org.apache.commons.io.FileUtils;
import org.camunda.bpm.application.impl.EjbProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.DeploymentWithDefinitions;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Collection;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Startup
@Singleton
class ProcessEngineApplication extends EjbProcessApplication {

    @BrewLog
    @Inject
    private ProcessEngine processEngine;

    private Logger logger = LogManager.getLogManager().getLogger(ProcessEngineApplication.class.getSimpleName());

    @PostConstruct
    private void postConstruct() {
        RepositoryService repositoryService = processEngine.getRepositoryService();


        DeploymentBuilder deployment = repositoryService.createDeployment().enableDuplicateFiltering(true);

        URL url = getClass().getResource("/processes");
        File root = new File(url.getPath());
        Collection<File> files = FileUtils.listFiles(root, new String[]{"bpmn"}, true);
        if (!files.isEmpty()) {
            files.forEach(file -> {
                try {
                    String filePath = file.getPath();
                    deployment.addInputStream(filePath, new FileInputStream(file));

                } catch (FileNotFoundException e) {
                    logger.severe("unable to deploy " + e.getMessage());
                }
            });

            DeploymentWithDefinitions deployWithResult = deployment.deployWithResult();
            logger.info(printDeployment(deployWithResult));
        } else {
            logger.info("no processes found for deployment");
        }

    }

    private String printDeployment(DeploymentWithDefinitions deployWithResult) {
        StringBuilder stringBuilder = new StringBuilder();

        if (null != deployWithResult && !deployWithResult.getDeployedProcessDefinitions().isEmpty()) {
            stringBuilder.append("deployed processes:").append("\n");

            for (ProcessDefinition processDefinition : deployWithResult.getDeployedProcessDefinitions()) {
                stringBuilder
                        .append("\n")
                        .append(String.format("\t\tv%s \t%s - %s\t\t(%s)", processDefinition.getVersion(), processDefinition.getName(), processDefinition.getKey(), processDefinition.getResourceName()));
            }
        }

        return stringBuilder.append("\n\n").toString();
    }

}

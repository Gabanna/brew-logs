package de.rgse.brewlogs.services;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import javax.inject.Inject;
import java.util.List;

public class ProcessService {

    private static final String MAIN_PROCESS = "Process_Gesamtprozess";

    @Inject
    private RuntimeService runtimeService;

    @Inject
    private TaskService taskService;

    public void deleteProcessForLog(long brewLogId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(Long.toString(brewLogId)).processDefinitionKey(MAIN_PROCESS).singleResult();
        if(processInstance != null) {
            runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(), "user cancaleation");
        } else {
            throw new IllegalStateException("no process found with id " + brewLogId);
        }
    }

    public List<Task> findTasksByLog(long brewLogId) {
        return taskService.createTaskQuery().processInstanceBusinessKey(Long.toString(brewLogId)).initializeFormKeys().list();
    }

    public void startProcess(long brewLogId) {
        runtimeService.startProcessInstanceByKey(MAIN_PROCESS, Long.toString(brewLogId));
    }
}

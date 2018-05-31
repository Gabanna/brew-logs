package de.rgse.brewlogs.services;

import de.rgse.brewlogs.api.vo.TaskVo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessService {

    private static final String MAIN_PROCESS = "Process_Gesamtprozess";

    @Inject
    private RuntimeService runtimeService;

    @Inject
    private TaskService taskService;

    @Inject
    private FormService formService;

    @Inject
    private FormResolver formResolver;

    public void deleteProcessForLog(long brewLogId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(Long.toString(brewLogId)).processDefinitionKey(MAIN_PROCESS).singleResult();
        if(processInstance != null) {
            runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(), "user cancaleation");
        } else {
            throw new IllegalStateException("no process found with id " + brewLogId);
        }
    }

    public List<TaskVo> findTasksByLog(long brewLogId) {
        return taskService.createTaskQuery().processInstanceBusinessKey(Long.toString(brewLogId)).initializeFormKeys().list().stream().map(task -> {
            TaskVo taskVo = TaskVo.of(task);
            taskVo.setForm(formResolver.readForm(task));
            return taskVo;
        }).collect(Collectors.toList());
    }

    public void startProcess(long brewLogId) {
        runtimeService.startProcessInstanceByKey(MAIN_PROCESS, Long.toString(brewLogId));
    }
}

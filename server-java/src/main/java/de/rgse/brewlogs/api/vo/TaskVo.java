package de.rgse.brewlogs.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;
import java.util.stream.Collectors;

public class TaskVo {

    @JsonProperty
    private String assignee;

    @JsonProperty
    private String form;

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String definitionKey;

    @JsonProperty
    private String processInstanceId;

    public String getAssignee() {
        return assignee;
    }

    public String getForm() {
        return form;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefinitionKey() {
        return definitionKey;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public static TaskVo of(Task task) {
        TaskVo result = new TaskVo();

        result.assignee = task.getAssignee();
        result.id = task.getId();
        result.name = task.getName();
        result.definitionKey = task.getTaskDefinitionKey();
        result.processInstanceId = task.getProcessInstanceId();
        result.form = readForm(task);

        return result;
    }

    public static List<TaskVo> of(List<Task> tasks) {
        return null == tasks ? Collections.emptyList() : tasks.stream().map(task -> TaskVo.of(task)).collect(Collectors.toList());
    }

    private static String readForm(Task task) {
        String result = null;
        String file = "/forms/" + task.getFormKey();
        try (InputStream inputStream = TaskVo.class.getResourceAsStream(file)){
            if(inputStream != null) {
                result = IOUtils.toString(inputStream, "UTF-8");
            }
        } catch(IOException e) {
            LogManager.getLogManager().getLogger(TaskVo.class.getCanonicalName()).log(java.util.logging.Level.SEVERE, "unable to load file " + file, e);
        }

        return result;
    }
}

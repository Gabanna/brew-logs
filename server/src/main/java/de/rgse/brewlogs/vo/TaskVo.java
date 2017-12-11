package de.rgse.brewlogs.vo;

import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.task.Task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class TaskVo {

	@JsonProperty
	private String id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String type;

	@JsonProperty
	private CaseState state;

	@JsonProperty
	private boolean required;

	@JsonProperty
	private String businessKey;

	@JsonProperty
	private String caseInstanceId;

	public TaskVo(CaseExecution task) {
		id = task.getId();
		name = task.getActivityName();
		type = task.getActivityType();
		required = task.isRequired();

		setState(task);

		if (CaseInstance.class.isAssignableFrom(task.getClass())) {
			CaseInstance caseInstance = (CaseInstance) task;
			businessKey = caseInstance.getBusinessKey();
			caseInstanceId = caseInstance.getCaseInstanceId();
		}
	}

	public TaskVo(Task task) {
		id = task.getId();
		name = task.getName();
	}

	public static List<TaskVo> parseCases(List<CaseExecution> list) {
		return list.stream().map(task -> new TaskVo(task)).collect(Collectors.toList());
	}
	
	public static List<TaskVo> parseTasks(List<Task> list) {
		return list.stream().map(task -> new TaskVo(task)).collect(Collectors.toList());
	}

	private void setState(CaseExecution task) {
		if (task.isActive()) {
			state = CaseState.ACTIVE;
		} else if (task.isAvailable()) {
			state = CaseState.AVAILABLE;
		} else if (task.isDisabled()) {
			state = CaseState.DISABLED;
		} else if (task.isEnabled()) {
			state = CaseState.ENABLED;
		} else if (task.isTerminated()) {
			state = CaseState.TERMINATED;
		}
	}
}

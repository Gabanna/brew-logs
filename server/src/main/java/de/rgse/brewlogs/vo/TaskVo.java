package de.rgse.brewlogs.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
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

	@JsonProperty
	private List<FormElementVo> form = new ArrayList<>();

	TaskVo() {
	}

	public TaskVo(CaseExecution task, String businessKey, List<FormElementVo> form) {
		this.form = form;
		id = task.getId();
		name = task.getActivityName();
		type = task.getActivityType();
		required = task.isRequired();
		this.businessKey = businessKey;

		setState(task);
	}

	public TaskVo(Task task, String businessKey, List<FormElementVo> form) {
		this.form = form;
		id = task.getId();
		name = task.getName();
		this.businessKey = businessKey;
	}

	public TaskVo(HistoricTaskInstance task, String businessKey) {
		id = task.getId();
		name = task.getName();
		this.businessKey = businessKey;

	}

	public TaskVo(HistoricTaskInstance task, String businessKey, List<FormElementVo> form) {
		id = task.getId();
		name = task.getName();
		this.form = form;
		this.businessKey = businessKey;

	}

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

	public List<FormElementVo> getForm() {
		return Collections.unmodifiableList(form);
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

	public String getName() {
		return name;
	}

	public String getBusinessKey() {
		return businessKey;
	}
}

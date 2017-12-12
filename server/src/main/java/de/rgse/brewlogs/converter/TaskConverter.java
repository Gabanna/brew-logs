package de.rgse.brewlogs.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.rgse.brewlogs.resolver.FormResolver;
import de.rgse.brewlogs.vo.TaskVo;

@Component
public class TaskConverter {

	private FormService formService;
	private FormResolver formResolver;
	private RuntimeService runtimeService;
	private CaseService caseService;

	@Autowired
	public TaskConverter(FormService formService, FormResolver formResolver, RuntimeService runtimeService,
			CaseService caseService) {
		this.formService = formService;
		this.formResolver = formResolver;
		this.runtimeService = runtimeService;
		this.caseService = caseService;
	}

	public TaskVo convertBpmn(Task task) {
		String businessKey = null;
		if (task.getProcessInstanceId() != null) {
			Optional<ProcessInstance> processInstance = Optional.ofNullable(runtimeService.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult());
			businessKey = processInstance.get().getBusinessKey();

		} else {
			CaseInstance caseInstance = caseService.createCaseInstanceQuery().caseInstanceId(task.getCaseInstanceId())
					.singleResult();
			businessKey = caseInstance.getBusinessKey();
		}

		String taskFormData = formService.getTaskFormData(task.getId()).getFormKey();
		return new TaskVo(task, businessKey, formResolver.getForm(taskFormData));
	}

	public List<TaskVo> parseCases(List<CaseExecution> list, FormService formService) {
		return list.stream().map(task -> new TaskVo(task, "", null/* formService.getTaskFormData(task.getId()) */))
				.collect(Collectors.toList());
	}

	public List<TaskVo> parseTasks(List<Task> list) {
		return list.stream().map(this::convertBpmn).collect(Collectors.toList());
	}
}

package de.rgse.brewlogs.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
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

	private FormResolver formResolver;
	private RuntimeService runtimeService;
	private HistoryService historyService;
	private CaseService caseService;

	@Autowired
	public TaskConverter(FormResolver formResolver, RuntimeService runtimeService, CaseService caseService,
			HistoryService historyService) {
		this.formResolver = formResolver;
		this.runtimeService = runtimeService;
		this.caseService = caseService;
		this.historyService = historyService;
	}

	public TaskVo convertCase(CaseExecution caseExecution) {
		CaseInstance instance = null;

		if (caseExecution instanceof CaseInstance) {
			instance = (CaseInstance) caseExecution;
		} else {
			instance = caseService.createCaseInstanceQuery().caseInstanceId(caseExecution.getCaseInstanceId())
					.singleResult();
		}

		return new TaskVo(caseExecution, instance.getBusinessKey());
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

		return new TaskVo(task, businessKey, formResolver.getForm(task.getId()));
	}

	public TaskVo convertHistoric(HistoricTaskInstance task) {
		String businessKey = null;
		if (task.getProcessInstanceId() != null) {
			Optional<HistoricProcessInstance> processInstance = Optional
					.ofNullable(historyService.createHistoricProcessInstanceQuery()
							.processInstanceId(task.getProcessInstanceId()).singleResult());
			businessKey = processInstance.get().getBusinessKey();

		} else {
			CaseInstance caseInstance = caseService.createCaseInstanceQuery().caseInstanceId(task.getCaseInstanceId())
					.singleResult();
			businessKey = caseInstance.getBusinessKey();
		}

		return new TaskVo(task, businessKey);
	}

	public List<TaskVo> parseCases(List<CaseExecution> list) {
		return list.stream().map(this::convertCase).collect(Collectors.toList());
	}

	public List<TaskVo> parseTasks(List<Task> list) {
		return list.stream().map(this::convertBpmn).collect(Collectors.toList());
	}

	public List<TaskVo> parseHistoricTasks(List<HistoricTaskInstance> historicTasks) {
		return historicTasks.stream().map(this::convertHistoric).collect(Collectors.toList());
	}

}

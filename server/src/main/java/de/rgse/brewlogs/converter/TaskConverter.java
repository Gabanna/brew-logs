package de.rgse.brewlogs.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.rgse.brewlogs.resolver.FormResolver;
import de.rgse.brewlogs.vo.TaskVo;

@Component
public class TaskConverter {

	@Autowired
	private FormService formService;
	
	@Autowired
	private FormResolver formResolver;
	
	public TaskVo convertBpmn(Task task) {
		String taskFormData = formService.getTaskFormData(task.getId()).getFormKey();
		return new TaskVo(task, formResolver.getForm(taskFormData));
	}
	
	public List<TaskVo> parseCases(List<CaseExecution> list, FormService formService) {
		return list.stream().map(task -> new TaskVo(task, null/*formService.getTaskFormData(task.getId())*/)).collect(Collectors.toList());
	}
	
	public List<TaskVo> parseTasks(List<Task> list) {
		return list.stream().map(this::convertBpmn).collect(Collectors.toList());
	}
}

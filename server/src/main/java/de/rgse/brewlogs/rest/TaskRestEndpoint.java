package de.rgse.brewlogs.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.rgse.brewlogs.vo.TaskVo;

@RestController
@RequestMapping("api")
public class TaskRestEndpoint {

	@Autowired
	private TaskService taskService;

	@Autowired
	private CaseService caseService;

	@Autowired
	private Logger logger;

	@RequestMapping(method = RequestMethod.GET, path = "brew-logs/{brewLogId}/tasks/active")
	@ResponseBody
	public ResponseEntity<List<TaskVo>> getPendingTasks(@PathVariable("brewLogId") String brewLogId) {
		ResponseEntity<List<TaskVo>> response = ResponseEntity.noContent().build();

		try {
			List<Task> bpmTasks = taskService.createTaskQuery().processInstanceBusinessKey(brewLogId).list();
			
			List<CaseExecution> cmmnTasks = caseService.createCaseExecutionQuery().caseInstanceBusinessKey(brewLogId)
					.active().list().stream().filter(task -> task.getActivityType().equals("humanTask"))
					.collect(Collectors.toList());
			
			List<TaskVo> tasks = TaskVo.parseTasks(bpmTasks);
			tasks.addAll(TaskVo.parseCases(cmmnTasks));

			response = ResponseEntity.ok().body(tasks);

		} catch (Exception exception) {
			response = ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
			logger.error("unable to process request", exception);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = "brew-logs/{brewLogId}/tasks/enabled")
	@ResponseBody
	public ResponseEntity<List<TaskVo>> getEnabledTasks(@PathVariable("brewLogId") String brewLogId) {
		ResponseEntity<List<TaskVo>> response = ResponseEntity.noContent().build();

		try {
			List<CaseExecution> cmmnTasks = caseService.createCaseExecutionQuery().caseInstanceBusinessKey(brewLogId)
					.enabled().list();
			List<TaskVo> tasks = TaskVo.parseCases(cmmnTasks);

			response = ResponseEntity.ok().body(tasks);

		} catch (Exception exception) {
			response = ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
			logger.error("unable to process request", exception);
		}

		return response;
	}
	

	@RequestMapping(method = RequestMethod.PUT, path = "tasks/{taskId}/start")
	@ResponseBody
	public ResponseEntity<TaskVo> getEnableTask(@PathVariable("taskId") String taskId) {
		ResponseEntity<TaskVo> response = ResponseEntity.noContent().build();

		try {
			caseService.manuallyStartCaseExecution(taskId);
			TaskVo vo = new TaskVo(caseService.createCaseExecutionQuery().caseExecutionId(taskId).singleResult());
			response = ResponseEntity.ok().body(vo);

		} catch (Exception exception) {
			response = ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
			logger.error("unable to process request", exception);
		}

		return response;
	}
}

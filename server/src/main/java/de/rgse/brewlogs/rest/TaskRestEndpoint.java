package de.rgse.brewlogs.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.rgse.brewlogs.converter.TaskConverter;
import de.rgse.brewlogs.model.BrewLog;
import de.rgse.brewlogs.model.BrewStep;
import de.rgse.brewlogs.repository.BrewLogRepository;
import de.rgse.brewlogs.repository.BrewStepRepository;
import de.rgse.brewlogs.vo.TaskVo;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class TaskRestEndpoint {

	private TaskService taskService;
	private CaseService caseService;
	private FormService formService;
	private TaskConverter taskconverter;
	private Logger logger;
	private BrewStepRepository brewStepRepository;
	private BrewLogRepository brewLogRepository;

	@Autowired
	public TaskRestEndpoint(TaskService taskService, CaseService caseService, FormService formService,
			TaskConverter taskconverter, HistoryService historyService, BrewStepRepository brewStepRepository, 
			BrewLogRepository brewLogRepository, Logger logger) {
		this.taskService = taskService;
		this.caseService = caseService;
		this.formService = formService;
		this.taskconverter = taskconverter;
		this.brewLogRepository = brewLogRepository;
		this.logger = logger;
	}

	@RequestMapping(method = RequestMethod.GET, path = "brew-logs/{brewLogId}/tasks/active")
	@ResponseBody
	public ResponseEntity<List<TaskVo>> getPendingTasks(@PathVariable("brewLogId") String brewLogId) {
		ResponseEntity<List<TaskVo>> response = ResponseEntity.noContent().build();

		try {
			List<Task> bpmTasks = taskService.createTaskQuery().processInstanceBusinessKey(brewLogId).list();
			bpmTasks = taskService.createTaskQuery().caseInstanceBusinessKey(brewLogId).list();
			List<TaskVo> tasks = taskconverter.parseTasks(bpmTasks);

			response = ResponseEntity.ok().body(tasks);

		} catch (Exception exception) {
			response = ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
			logger.error("unable to process request", exception);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = "brew-logs/{brewLogId}/tasks/history")
	@ResponseBody
	public ResponseEntity<List<BrewStep>> getHistoryTasks(@PathVariable("brewLogId") String brewLogId) {
		ResponseEntity<List<BrewStep>> response = ResponseEntity.noContent().build();

		try {
			List<BrewStep> brewSteps = brewStepRepository.findByBrewLogOrderByCreated(Long.valueOf(brewLogId));

			response = ResponseEntity.ok().body(brewSteps);

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
			List<TaskVo> tasks = taskconverter.parseCases(cmmnTasks, formService);

			response = ResponseEntity.ok().body(tasks);

		} catch (Exception exception) {
			response = ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
			logger.error("unable to process request", exception);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "tasks/{taskId}/start")
	@ResponseBody
	public ResponseEntity<TaskVo> enableTask(@PathVariable("taskId") String taskId) {
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

	@RequestMapping(method = RequestMethod.POST, path = "tasks/{taskId}")
	@ResponseBody
	public ResponseEntity<BrewStep> finishTask(@PathVariable("taskId") String taskId, @RequestBody TaskVo task) {
		ResponseEntity<BrewStep> response = ResponseEntity.noContent().build();

		try {
			taskService.complete(taskId);
			BrewLog brewLog = brewLogRepository.findOne(Long.valueOf(task.getBusinessKey()));
			BrewStep brewStep = brewStepRepository.save(new BrewStep(brewLog, task));
			response = ResponseEntity.ok().body(brewStep);

		} catch (Exception exception) {
			response = ResponseEntity.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
			logger.error("unable to process request", exception);
		}

		return response;
	}
}

package de.rgse.brewlogs.rest;

import java.util.List;

import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.rgse.brewlogs.vo.TaskVo;

@RestController
@RequestMapping("api")
public class TaskRestEndpoint {

	@Inject
	private TaskService taskService;

	@RequestMapping(method = RequestMethod.GET, path = "/tasks")
	@ResponseBody
	public List<TaskVo> getPendingTasks(@RequestParam(value = "brewLogId", required = false) String brewLogId) {

		TaskQuery query = taskService.createTaskQuery();
		
		if (brewLogId != null) {
			query.caseInstanceBusinessKey(brewLogId).processInstanceBusinessKey(brewLogId);
		}

		return TaskVo.parseTask(query.list());
	}
}

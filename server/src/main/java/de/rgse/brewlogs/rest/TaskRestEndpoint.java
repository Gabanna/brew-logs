package de.rgse.brewlogs.rest;

import java.util.List;

import javax.inject.Inject;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseExecutionQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.rgse.brewlogs.vo.CaseState;
import de.rgse.brewlogs.vo.TaskVo;

@RestController
@RequestMapping("api")
public class TaskRestEndpoint {

	@Inject
	private CaseService caseService;

	@RequestMapping(method = RequestMethod.GET, path = "/tasks")
	@ResponseBody
	public List<TaskVo> getPendingTasks(@RequestParam(value = "brewLogId", required = false) String brewLogId,
			@RequestParam(value = "state", required = false) String state) {

		CaseExecutionQuery query = caseService.createCaseExecutionQuery();

		if (state != null) {
			CaseState caseState = CaseState.parse(state);
			if (caseState == CaseState.ACTIVE) {
				query.active();
				
			} else if (caseState == CaseState.AVAILABLE) {
				query.available();

			} else if (caseState == CaseState.ENABLED) {
				query.enabled();

			} else if (caseState == CaseState.DISABLED) {
				query.disabled();

			}
		}

		if (brewLogId != null) {
			query.caseInstanceBusinessKey(brewLogId);
		}

		return TaskVo.parse(query.list());
	}
}

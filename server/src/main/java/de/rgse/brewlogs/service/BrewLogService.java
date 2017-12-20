package de.rgse.brewlogs.service;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.rgse.brewlogs.model.BrewLog;
import de.rgse.brewlogs.repository.BrewLogRepository;

@Component
public class BrewLogService {

	@Autowired
	private BrewLogRepository repository;
	
	@Autowired
	private RuntimeService runtimeService;
	
	public BrewLog startNewBrewLog(String subject, String name) {
		BrewLog brewLog= repository.save(new BrewLog(subject, name));
		runtimeService.startProcessInstanceByKey("Process_Brauprozess", brewLog.getId().toString());
		return brewLog;
	}
}

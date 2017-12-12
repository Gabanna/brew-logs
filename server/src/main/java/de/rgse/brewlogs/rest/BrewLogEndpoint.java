package de.rgse.brewlogs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.rgse.brewlogs.model.BrewLog;
import de.rgse.brewlogs.repository.BrewLogRepository;

@RestController
@RequestMapping("api")
public class BrewLogEndpoint {

	private BrewLogRepository brewLogRepository;

	@Autowired
	public BrewLogEndpoint(BrewLogRepository brewLogRepository) {
		super();
		this.brewLogRepository = brewLogRepository;
	}

	@RequestMapping(method = RequestMethod.GET, path = "brew-logs/{subject}")
	@ResponseBody
	public List<BrewLog> getBrewLogs(@PathVariable("subject") String subject) {
		return brewLogRepository.findBySubject(subject);
	}
}

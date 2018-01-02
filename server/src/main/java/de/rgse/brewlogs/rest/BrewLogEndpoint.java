package de.rgse.brewlogs.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.rgse.brewlogs.model.BrewLog;
import de.rgse.brewlogs.repository.BrewLogRepository;
import de.rgse.brewlogs.service.BrewLogService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class BrewLogEndpoint {

	private BrewLogRepository brewLogRepository;
	private BrewLogService brewLogService;

	@Autowired
	public BrewLogEndpoint(BrewLogRepository brewLogRepository, BrewLogService brewLogService) {
		this.brewLogRepository = brewLogRepository;
		this.brewLogService = brewLogService;
	}

	@GetMapping("brew-logs/{subject:.+}")
	@ResponseBody
	public List<BrewLog> getBrewLogs(@PathVariable("subject") String subject) {
		List<BrewLog> list = brewLogRepository.findBySubject(subject);
		return list;
	}

	@PostMapping("brew-logs")
	@ResponseBody
	public ResponseEntity<?> createBrewLog(@RequestBody Map<String, Object> data) {
		ResponseEntity<?> response = ResponseEntity.noContent().build();
		
		try {
			String name = (String) data.get("name");
			String subject = (String) data.get("subject");
			BrewLog brewLog = brewLogService.startNewBrewLog(subject, name);
			response = ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(brewLog);
			
		} catch (Exception exception) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getLocalizedMessage());
		}
		
		return response;
	}
}

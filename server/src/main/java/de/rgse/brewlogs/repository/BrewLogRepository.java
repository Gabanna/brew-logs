package de.rgse.brewlogs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.rgse.brewlogs.model.BrewLog;

public interface BrewLogRepository extends CrudRepository<BrewLog, Long> {

	List<BrewLog> findByName(String name);
	
	List<BrewLog> findBySubject(String subject);
}

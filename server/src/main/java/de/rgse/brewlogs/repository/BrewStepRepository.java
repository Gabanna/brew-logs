package de.rgse.brewlogs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.rgse.brewlogs.model.BrewStep;

public interface BrewStepRepository extends CrudRepository<BrewStep, Long> {

	List<BrewStep> findByBrewLogIdOrderByCreatedAsc(Long id);
}

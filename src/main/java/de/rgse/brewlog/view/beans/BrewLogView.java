package de.rgse.brewlog.view.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.repository.BrewLogRepository;

@Named
@RequestScoped
public class BrewLogView {

	@Inject @Brew
	private BrewLogRepository repository;
	
	private void postConstruct() {
		
	}
}

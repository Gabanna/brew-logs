package de.rgse.brewlog.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.rgse.brewlog.decorators.Brew;

@Dependent
public class Producers {

	@PersistenceContext(unitName = "brew-logs-pu")
	private EntityManager entityManager;

	@Brew
	@Produces
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Produces @Brew
	public Logger produceLogger(InjectionPoint point) {
		return LoggerFactory.getLogger(point.getBean().getBeanClass());
	}
	
}

package de.rgse.brewlog;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}

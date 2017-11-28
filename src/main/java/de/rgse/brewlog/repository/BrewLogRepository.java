package de.rgse.brewlog.repository;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.BrewLog;

@Brew
@Dependent
public class BrewLogRepository extends EntityRepository<BrewLog> implements Serializable {

	@Inject
	public BrewLogRepository(@Brew EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void add(BrewLog entity) {

		if (entity.getId() == null) {
			getEntityManager().persist(entity);

		} else {
			getEntityManager().merge(entity);
		}
	}

	@Override
	public BrewLog update(BrewLog entity) {
		return getEntityManager().merge(entity);
	}

}

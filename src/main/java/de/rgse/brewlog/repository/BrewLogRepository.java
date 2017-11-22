package de.rgse.brewlog.repository;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.querydsl.core.QueryMetadata;
import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.BrewLog;

@Dependent
public class BrewLogRepository implements EntityRepository<BrewLog>{

	@Inject @Brew
	private EntityManager em;

	@Override
	public void add(BrewLog entity) {
		
		if(entity.getId() == null) {
			em.persist(entity);
		
		} else {
			em.merge(entity);
		}
	}

	@Override
	public void remove(BrewLog entity) {
		BrewLog brewLog = em.find(BrewLog.class, entity.getId());
		em.remove(brewLog);
	}

	@Override
	public BrewLog update(BrewLog entity) {
		return em.merge(entity);
	}

	@Override
	public List<BrewLog> query(Specification<BrewLog> specification) {
		JPAQuery<BrewLog> query = specification.getQuery();
		
		QueryMetadata metadata = query.getMetadata();
		return new JPAQuery<BrewLog>(em, metadata).fetch();
	}
	
}

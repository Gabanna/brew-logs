package de.rgse.brewlog.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.IdProvider;

public abstract class EntityRepository<T extends IdProvider<?>> {

	private EntityManager entityManager;
	
	@Inject
	public EntityRepository(@Brew EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public <E> Optional<T> find(Class<T> clazz, E id) {
		return Optional.ofNullable(entityManager.find(clazz, id));
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public abstract void add(@NotNull T entity);
	
    public void remove(@NotNull T entity) {
    	IdProvider<?> provider = getEntityManager().find(entity.getClass(), entity.getId());
    	getEntityManager().remove(provider);
    }
    
    public abstract T update(@NotNull T entity); // Think it as replace for set
 
    public <E> List<E> query(@NotNull Specification<E> specification) {
    	return new JPAQuery<E>(getEntityManager(), specification.getQuery().getMetadata()).fetch();
    }
    
}

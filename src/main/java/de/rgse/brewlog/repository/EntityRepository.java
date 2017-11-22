package de.rgse.brewlog.repository;

import java.util.List;

import de.rgse.brewlog.domain.BaseDomain;

public interface EntityRepository<T extends BaseDomain<?>> {

	void add(T entity);
    void remove(T entity);
    T update(T entity); // Think it as replace for set
 
    List<T> query(Specification<T> specification);
}

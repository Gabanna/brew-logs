package de.rgse.brewlog.repository;

import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.domain.BaseDomain;

public interface Specification<T extends BaseDomain<?>> {

	JPAQuery<T> getQuery();
}

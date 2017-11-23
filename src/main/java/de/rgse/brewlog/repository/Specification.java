package de.rgse.brewlog.repository;

import com.querydsl.jpa.impl.JPAQuery;

public interface Specification<T> {

	JPAQuery<T> getQuery();
}

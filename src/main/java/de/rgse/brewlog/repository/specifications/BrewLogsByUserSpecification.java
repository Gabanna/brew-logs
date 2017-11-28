package de.rgse.brewlog.repository.specifications;

import javax.validation.constraints.NotNull;

import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.domain.BrewLog;
import de.rgse.brewlog.domain.QBrewLog;
import de.rgse.brewlog.repository.Specification;

public class BrewLogsByUserSpecification implements Specification<BrewLog>{

	private String userName;

	public BrewLogsByUserSpecification(@NotNull String userName) {
		this.userName = userName;
	}
	
	@Override
	public JPAQuery<BrewLog> getQuery() {
		QBrewLog brewLog = QBrewLog.brewLog;
		return new JPAQuery<BrewLog>().from(brewLog).where(brewLog.user.userName.eq(userName));
	}

}

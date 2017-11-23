package de.rgse.brewlog.repository.specifications;

import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.domain.auth.QUser;
import de.rgse.brewlog.domain.auth.User;
import de.rgse.brewlog.repository.Specification;

public class UserNameCountSpecification implements Specification<Long>{
	
	private String userName;

	public UserNameCountSpecification(String userName) {
		this.userName = userName;
	}
	
	@Override
	public JPAQuery<Long> getQuery() {
		QUser user = QUser.user;
		return new JPAQuery<User>().select(user.userName.count()).from(user).where(user.userName.eq(userName));
	}

}

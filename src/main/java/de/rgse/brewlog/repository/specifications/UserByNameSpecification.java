package de.rgse.brewlog.repository.specifications;

import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.domain.auth.QUser;
import de.rgse.brewlog.domain.auth.User;
import de.rgse.brewlog.repository.Specification;

public class UserByNameSpecification implements Specification<User>{

	private String userName;

	public UserByNameSpecification(String userName) {
		this.userName = userName;
	}
	
	@Override
	public JPAQuery<User> getQuery() {
		QUser user = QUser.user;
		return new JPAQuery<User>().from(user).where(user.userName.eq(userName));
	}

}

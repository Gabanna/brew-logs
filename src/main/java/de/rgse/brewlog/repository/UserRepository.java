package de.rgse.brewlog.repository;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.auth.Role;
import de.rgse.brewlog.domain.auth.User;

@Brew
@Dependent
public class UserRepository extends EntityRepository<User> implements Serializable {
	
	@Inject
	public UserRepository(@Brew EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public void add(User entity) {
		if(entity.getRoles().isEmpty()) {
			Role find = getEntityManager().find(Role.class, "DEFAULT");
			if(find != null) {
				entity.getRoles().add(find);
			}
		}
		getEntityManager().persist(entity);
	}

	@Override
	public User update(User entity) {
		return getEntityManager().merge(entity);
	}

}

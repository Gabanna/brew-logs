package de.rgse.brewlog.repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.auth.Role;
import de.rgse.brewlog.domain.auth.User;

@Dependent
public class UserRepository extends EntityRepository<User> {
	
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

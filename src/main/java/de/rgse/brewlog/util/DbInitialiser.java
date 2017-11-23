package de.rgse.brewlog.util;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.auth.Role;

@Stateless
@Startup
public class DbInitialiser {

	@Inject @Brew
	private EntityManager entityManager;

	@Transactional
	@PostConstruct
	private void initDb() {
		Role role = entityManager.find(Role.class, "DEFAULT");
		
		if(role == null) {
			role = new Role("DEFAULT");
			role.addPermission("CREATE_BREW_LOG");
			
			entityManager.persist(role);
		}
	}

}

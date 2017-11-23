package de.rgse.brewlog.domain.auth;

import java.util.Collections;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.infinispan.util.concurrent.ConcurrentHashSet;

import de.rgse.brewlog.domain.IdProvider;

@Entity
public class Role implements IdProvider<String> {

	@Id
	private String name;

	@ElementCollection
	private Set<String> permissions;

	protected Role() {
		permissions = new ConcurrentHashSet<>();
	}

	public Role(String name) {
		this.name = name;
		permissions = new ConcurrentHashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getId() {
		return name;
	}

	public void addPermission(String permission) {
		this.permissions.add(permission);
	}

	public Set<String> getPermissions() {
		return Collections.unmodifiableSet(permissions);
	}

}

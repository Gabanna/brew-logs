package de.rgse.brewlog.domain.auth;

import javax.persistence.Entity;
import javax.persistence.Id;

import de.rgse.brewlog.domain.IdProvider;

@Entity
public class Permission implements IdProvider<String> {

	@Id
	private String name;

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

}

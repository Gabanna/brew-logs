package de.rgse.brewlog.domain;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class BeerType extends BaseDomain<Long> {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
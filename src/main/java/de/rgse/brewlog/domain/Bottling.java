package de.rgse.brewlog.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class Bottling extends BrewStep {

	@OneToMany
	private List<QuantifyableItem> containers;

	public List<QuantifyableItem> getContainers() {
		return containers;
	}

	public void setContainers(List<QuantifyableItem> containers) {
		this.containers = containers;
	}

}
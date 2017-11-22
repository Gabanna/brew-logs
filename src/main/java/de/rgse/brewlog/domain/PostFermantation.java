package de.rgse.brewlog.domain;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class PostFermantation extends BrewStep {

	private int temperature;

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

}

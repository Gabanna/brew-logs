package de.rgse.brewlogs.domain.ingredient;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ingredient implements Serializable {

	private String name;

	public Ingredient() {
	}

	public Ingredient(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ingredient [name=" + name + "]";
	}

}

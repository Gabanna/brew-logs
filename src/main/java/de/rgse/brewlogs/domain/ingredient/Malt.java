package de.rgse.brewlogs.domain.ingredient;

@SuppressWarnings("serial")
public class Malt extends Ingredient {

	private String ebc;

	public Malt(String name, String ebc) {
		super(name);
		this.ebc = ebc;
	}

	public String getEbc() {
		return ebc;
	}
}

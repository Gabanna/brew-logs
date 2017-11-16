package de.rgse.brewlogs.domain.ingredient;

@SuppressWarnings("serial")
public class Yeast extends Ingredient {

	private YeastType yeastType;

	public YeastType getYeastType() {
		return yeastType;
	}

	public enum YeastType {
		TOP_FERMENTED, BOTTOM_FERMENTED;
	}
}

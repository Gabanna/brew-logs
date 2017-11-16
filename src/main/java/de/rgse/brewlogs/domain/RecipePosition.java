package de.rgse.brewlogs.domain;

public class RecipePosition<T extends Ingredient> {

	private T ingredient;
	private float quantity;
	private Unit unit;
}

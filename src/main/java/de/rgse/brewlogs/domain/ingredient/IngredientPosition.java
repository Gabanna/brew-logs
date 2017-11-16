package de.rgse.brewlogs.domain.ingredient;

public class IngredientPosition<T extends Ingredient> {

	private int quantity;
	private T ingredient;

	public IngredientPosition(int quantity, T ingredient) {
		this.quantity = quantity;
		this.ingredient = ingredient;
	}

	public int getQuantity() {
		return quantity;
	}

	public T getIngredient() {
		return ingredient;
	}

	@Override
	public String toString() {
		return "IngredientPosition [quantity=" + quantity + ", ingredient=" + ingredient + "]";
	}

}

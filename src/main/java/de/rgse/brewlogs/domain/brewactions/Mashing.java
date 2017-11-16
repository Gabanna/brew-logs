package de.rgse.brewlogs.domain.brewactions;

import java.util.ArrayList;
import java.util.List;

import de.rgse.brewlogs.domain.ingredient.IngredientPosition;
import de.rgse.brewlogs.domain.ingredient.Malt;

public class Mashing extends BrewAction {

	private int waterVolume;
	private List<IngredientPosition<Malt>> positions;

	public Mashing() {
		positions = new ArrayList<>();
	}

	public int getWaterVolume() {
		return waterVolume;
	}

	public void setWaterVolume(int waterVolume) {
		this.waterVolume = waterVolume;
	}

	public List<IngredientPosition<Malt>> getPositions() {
		return positions;
	}

	public void setPositions(List<IngredientPosition<Malt>> positions) {
		this.positions = positions;
	}

	@Override
	public String toString() {
		return "Mashing [waterVolume=" + waterVolume + ", positions=" + positions + "]";
	}

}

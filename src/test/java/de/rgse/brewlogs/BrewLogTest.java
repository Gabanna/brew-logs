package de.rgse.brewlogs;

import java.time.Instant;

import org.junit.Test;

import de.rgse.brewlogs.domain.BrewLog;
import de.rgse.brewlogs.domain.BrewLogEntry;
import de.rgse.brewlogs.domain.brewactions.Mashing;
import de.rgse.brewlogs.domain.ingredient.IngredientPosition;
import de.rgse.brewlogs.domain.ingredient.Malt;

public class BrewLogTest {

	@Test
	public void test() {
		Malt malt = new Malt("MyMalt", "123");

		IngredientPosition<Malt> position = new IngredientPosition<>(4000, malt);

		Mashing mashing = new Mashing();
		mashing.setWaterVolume(20);
		mashing.getPositions().add(position);

		BrewLogEntry brewLogEntry = new BrewLogEntry();
		brewLogEntry.setTime(Instant.now());
		brewLogEntry.setAction(mashing);

		BrewLog brewLog = new BrewLog();
		brewLog.getBrewLogEntries().add(brewLogEntry);

		System.out.println(brewLog);
	}
}

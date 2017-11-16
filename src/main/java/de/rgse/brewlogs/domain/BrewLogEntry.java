package de.rgse.brewlogs.domain;

import java.time.Instant;

import de.rgse.brewlogs.domain.brewactions.BrewAction;

public class BrewLogEntry {

	private Instant time;
	private BrewAction action;

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public BrewAction getAction() {
		return action;
	}

	public void setAction(BrewAction action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "BrewLogEntry [time=" + time + ", action=" + action + "]";
	}

	
}

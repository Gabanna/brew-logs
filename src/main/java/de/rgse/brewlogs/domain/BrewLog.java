package de.rgse.brewlogs.domain;

import java.util.ArrayList;
import java.util.List;

public class BrewLog {

	private List<BrewLogEntry> brewLogEntries;

	public BrewLog() {
		brewLogEntries = new ArrayList<>();
	}
	
	public List<BrewLogEntry> getBrewLogEntries() {
		return brewLogEntries;
	}

	public void setBrewLogEntries(List<BrewLogEntry> brewLogEntries) {
		this.brewLogEntries = brewLogEntries;
	}

	@Override
	public String toString() {
		return "BrewLog [brewLogEntries=" + brewLogEntries + "]";
	}

}

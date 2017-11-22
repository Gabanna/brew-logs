package de.rgse.brewlog.domain;

import java.time.Duration;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class TeperatureDurationPair extends BaseDomain<Long> {

	private Integer temperature;

	private Duration duration;

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

}
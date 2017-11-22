package de.rgse.brewlog.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Fermentation extends BrewStep {

	private LocalDateTime fermentationStart;

	private Integer temperature;

	private Integer wortValue;

	private String wortCorrection;

	public LocalDateTime getFermentationStart() {
		return fermentationStart;
	}

	public void setFermentationStart(LocalDateTime fermentationStart) {
		this.fermentationStart = fermentationStart;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getWortValue() {
		return wortValue;
	}

	public void setWortValue(Integer wortValue) {
		this.wortValue = wortValue;
	}

	public String getWortCorrection() {
		return wortCorrection;
	}

	public void setWortCorrection(String wortCorrection) {
		this.wortCorrection = wortCorrection;
	}

}
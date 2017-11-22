package de.rgse.brewlog.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class Tasting extends BrewStep {

	@OneToOne
	private Evaluation taste;

	@OneToOne
	private Evaluation froth;

	@OneToOne
	private Evaluation colour;

	@OneToOne
	private Evaluation charbonation;

	private String error;

	public Evaluation getFroth() {
		return froth;
	}

	public void setFroth(Evaluation froth) {
		this.froth = froth;
	}

	public Evaluation getColour() {
		return colour;
	}

	public void setColour(Evaluation colour) {
		this.colour = colour;
	}

	public Evaluation getCharbonation() {
		return charbonation;
	}

	public void setCharbonation(Evaluation charbonation) {
		this.charbonation = charbonation;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}

package de.rgse.brewlog.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class Mashing extends BrewStep {

	@OneToOne
	private TeperatureDurationPair startMashing;

	@OneToOne
	private TeperatureDurationPair proteinRest;

	@OneToOne
	private TeperatureDurationPair maltoseRest;

	@OneToOne
	private TeperatureDurationPair saccharification;

	@OneToOne
	private TeperatureDurationPair reSaccharification;

	@OneToMany
	private List<IodineTest> iodineTests;

	private LocalDateTime purification;

	private Integer rehydration;

	@OneToOne
	private Wort wortTarget;

	@OneToOne
	private Wort wortActual;

	public TeperatureDurationPair getStartMashing() {
		return startMashing;
	}

	public void setStartMashing(TeperatureDurationPair startMashing) {
		this.startMashing = startMashing;
	}

	public TeperatureDurationPair getProteinRest() {
		return proteinRest;
	}

	public void setProteinRest(TeperatureDurationPair proteinRest) {
		this.proteinRest = proteinRest;
	}

	public TeperatureDurationPair getMaltoseRest() {
		return maltoseRest;
	}

	public void setMaltoseRest(TeperatureDurationPair maltoseRest) {
		this.maltoseRest = maltoseRest;
	}

	public TeperatureDurationPair getSaccharification() {
		return saccharification;
	}

	public void setSaccharification(TeperatureDurationPair saccharification) {
		this.saccharification = saccharification;
	}

	public TeperatureDurationPair getReSaccharification() {
		return reSaccharification;
	}

	public void setReSaccharification(TeperatureDurationPair reSaccharification) {
		this.reSaccharification = reSaccharification;
	}

	public List<IodineTest> getIodineTests() {
		return iodineTests;
	}

	public void setIodineTests(List<IodineTest> iodineTests) {
		this.iodineTests = iodineTests;
	}

	public LocalDateTime getPurification() {
		return purification;
	}

	public void setPurification(LocalDateTime purification) {
		this.purification = purification;
	}

	public Integer getRehydration() {
		return rehydration;
	}

	public void setRehydration(Integer rehydration) {
		this.rehydration = rehydration;
	}

	public Wort getWortTarget() {
		return wortTarget;
	}

	public void setWortTarget(Wort wortTarget) {
		this.wortTarget = wortTarget;
	}

	public Wort getWortActual() {
		return wortActual;
	}

	public void setWortActual(Wort wortActual) {
		this.wortActual = wortActual;
	}

}
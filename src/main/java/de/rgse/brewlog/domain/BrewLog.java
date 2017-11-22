package de.rgse.brewlog.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class BrewLog extends BaseDomain<Long> {

	private String name;

	private Boolean completed;

	@OneToOne
	private Mashing mashing;

	@OneToOne
	private Boiling boiling;

	@OneToOne
	private Fermentation fermentation;

	@OneToOne
	private Bottling bottling;

	@OneToOne
	private PostFermantation postFermantation;

	@OneToOne
	private BeerType type;

	@OneToOne
	private Tasting tasting;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Mashing getMashing() {
		return mashing;
	}

	public void setMashing(Mashing mashing) {
		this.mashing = mashing;
	}

	public Boiling getBoiling() {
		return boiling;
	}

	public void setBoiling(Boiling boiling) {
		this.boiling = boiling;
	}

	public Fermentation getFermentation() {
		return fermentation;
	}

	public void setFermentation(Fermentation fermentation) {
		this.fermentation = fermentation;
	}

	public Bottling getBottling() {
		return bottling;
	}

	public void setBottling(Bottling bottling) {
		this.bottling = bottling;
	}

	public PostFermantation getPostFermantation() {
		return postFermantation;
	}

	public void setPostFermantation(PostFermantation postFermantation) {
		this.postFermantation = postFermantation;
	}

	public Tasting getTasting() {
		return tasting;
	}

	public void setTasting(Tasting tasting) {
		this.tasting = tasting;
	}

	public BeerType getType() {
		return type;
	}

	public void setType(BeerType type) {
		this.type = type;
	}

	public LocalDateTime getStartTime() {
		return mashing == null ? null : mashing.getStart();
	}

	public LocalDateTime getEndTime() {
		return tasting == null ? null : tasting.getEnd();
	}

}
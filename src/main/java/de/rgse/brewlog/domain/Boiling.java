package de.rgse.brewlog.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class Boiling extends BrewStep {

	private LocalDateTime purification;

	private Integer cooldown;

	private Integer wortValue;

	@OneToMany
	private List<QuantifyableItem> hoppings;

	@OneToMany
	private List<QuantifyableItem> yeasting;

	public List<QuantifyableItem> getHoppings() {
		return hoppings;
	}

	public void setHoppings(List<QuantifyableItem> hoppings) {
		this.hoppings = hoppings;
	}

	public LocalDateTime getPurification() {
		return purification;
	}

	public void setPurification(LocalDateTime purification) {
		this.purification = purification;
	}

	public Integer getCooldown() {
		return cooldown;
	}

	public void setCooldown(Integer cooldown) {
		this.cooldown = cooldown;
	}

	public Integer getWortValue() {
		return wortValue;
	}

	public void setWortValue(Integer wortValue) {
		this.wortValue = wortValue;
	}

	public List<QuantifyableItem> getYeasting() {
		return yeasting;
	}

	public void setYeasting(List<QuantifyableItem> yeasting) {
		this.yeasting = yeasting;
	}

}
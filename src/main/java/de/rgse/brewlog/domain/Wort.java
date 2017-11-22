package de.rgse.brewlog.domain;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Wort extends BaseDomain<Long> {

	private Integer plato;

	private Integer liter;

	public Integer getPlato() {
		return plato;
	}

	public void setPlato(Integer plato) {
		this.plato = plato;
	}

	public Integer getLiter() {
		return liter;
	}

	public void setLiter(Integer liter) {
		this.liter = liter;
	}

}
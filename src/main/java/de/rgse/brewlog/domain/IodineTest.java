package de.rgse.brewlog.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class IodineTest extends BaseDomain<Long> {

	private LocalDateTime time;

	private Integer valuePerCent;

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Integer getValuePerCent() {
		return valuePerCent;
	}

	public void setValuePerCent(Integer valuePerCent) {
		this.valuePerCent = valuePerCent;
	}

}
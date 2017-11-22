package de.rgse.brewlog.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BrewStep extends BaseDomain<Long> {

	private LocalDateTime start;

	private LocalDateTime end;

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

}
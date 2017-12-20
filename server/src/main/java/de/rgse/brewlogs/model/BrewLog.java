package de.rgse.brewlogs.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BrewLog extends BaseEntity {

	@JsonProperty
	private String name;

	@JsonProperty
	private String subject;

	protected BrewLog() {}
	
	public BrewLog(String subject, String name) {
		this.subject = subject;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}

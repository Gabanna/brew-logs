package de.rgse.brewlogs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BrewLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty
	private Long id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String subject;

	protected BrewLog() {
	}

	public BrewLog(String subject) {
		this.subject = subject;
	}

	public Long getId() {
		return id;
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

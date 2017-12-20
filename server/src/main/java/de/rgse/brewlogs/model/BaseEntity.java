package de.rgse.brewlogs.model;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.rgse.brewlogs.converter.InstantDeserializer;
import de.rgse.brewlogs.converter.InstantSerializer;

@MappedSuperclass
public class BaseEntity {

	@Id
	@JsonProperty
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonDeserialize(using = InstantDeserializer.class)
	@JsonSerialize(using = InstantSerializer.class)
	@JsonProperty
	private Instant created;

	@JsonDeserialize(using = InstantDeserializer.class)
	@JsonSerialize(using = InstantSerializer.class)
	@JsonProperty
	private Instant updated;

	@PrePersist
	private void prePersist() {
		this.created = Instant.now();
	}

	@PreUpdate
	private void preUpdate() {
		this.updated = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public Instant getCreated() {
		return created;
	}

	public Instant getUpdated() {
		return updated;
	}

}

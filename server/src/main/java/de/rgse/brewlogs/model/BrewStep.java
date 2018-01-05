package de.rgse.brewlogs.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.rgse.brewlogs.converter.InstantDeserializer;
import de.rgse.brewlogs.converter.InstantSerializer;
import de.rgse.brewlogs.vo.TaskVo;

@Entity
public class BrewStep extends BaseEntity {

	private Long brewLogId;

	@JsonProperty
	private String action;

	@JsonProperty
	@OneToMany(cascade = CascadeType.ALL)
	private List<FormElement> elements = new ArrayList<>();

	@JsonDeserialize(using = InstantDeserializer.class)
	@JsonSerialize(using = InstantSerializer.class)
	@JsonProperty
	private Instant started;

	@JsonDeserialize(using = InstantDeserializer.class)
	@JsonSerialize(using = InstantSerializer.class)
	@JsonProperty
	private Instant terminated;

	protected BrewStep() {
	}

	public BrewStep(Long brewLogId, TaskVo task) {
		this.brewLogId = brewLogId;
		action = task.getName();
		elements = task.getForm().stream().map(form -> new FormElement(form)).collect(Collectors.toList());
		this.started = task.getStarted() != null ? task.getStarted().toInstant() : null;
		this.terminated = task.getTerminated() != null ? task.getTerminated().toInstant() : Instant.now();
	}

	public Long getBrewLogId() {
		return brewLogId;
	}

	public void setBrewLogId(Long brewLogId) {
		this.brewLogId = brewLogId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<FormElement> getElements() {
		return elements;
	}

	public void setElements(List<FormElement> elements) {
		this.elements = elements;
	}

	public Instant getStarted() {
		return started;
	}

	public void setStarted(Instant started) {
		this.started = started;
	}

	public Instant getTerminated() {
		return terminated;
	}

	public void setTerminated(Instant terminated) {
		this.terminated = terminated;
	}

}

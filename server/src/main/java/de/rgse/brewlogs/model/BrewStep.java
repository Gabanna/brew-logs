package de.rgse.brewlogs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.rgse.brewlogs.vo.TaskVo;

@Entity
public class BrewStep extends BaseEntity {

	private Long brewLogId;

	@JsonProperty
	private String action;

	@JsonProperty
	@OneToMany(cascade = CascadeType.ALL)
	private List<FormElement> elements = new ArrayList<>();

	protected BrewStep() {
	}

	public BrewStep(Long brewLogId, TaskVo task) {
		this.brewLogId = brewLogId;
		action = task.getName();
		elements = task.getForm().stream().map(form -> new FormElement(form)).collect(Collectors.toList());
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

}

package de.rgse.brewlogs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.rgse.brewlogs.vo.TaskVo;

@Entity
public class BrewStep extends BaseEntity {

	@ManyToOne
	private BrewLog brewLog;

	@JsonProperty
	private String action;

	@JsonProperty
	@OneToMany(cascade = CascadeType.ALL)
	private List<FormElement> elements = new ArrayList<>();

	protected BrewStep() {
	}

	public BrewStep(BrewLog brewLog, TaskVo task) {
		this.brewLog = brewLog;
		action = task.getName();
		elements = task.getForm().stream().map(form -> new FormElement(form)).collect(Collectors.toList());
	}

	public BrewLog getBrewLog() {
		return brewLog;
	}

	public void setBrewLog(BrewLog brewLog) {
		this.brewLog = brewLog;
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

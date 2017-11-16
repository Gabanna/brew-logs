package de.rgse.brewlogs.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseEntity<ID_TYPE> implements Serializable {

	private ID_TYPE id;

	public ID_TYPE getId() {
		return id;
	}

	public void setId(ID_TYPE id) {
		this.id = id;
	}
}

package de.rgse.brewlogs.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.rgse.brewlogs.vo.FormElementVo;
import de.rgse.brewlogs.vo.FormElementVo.FormElementType;

@Entity
public class FormElement extends BaseEntity {

	private String name;
	@Enumerated(EnumType.STRING)
	private FormElementType type;
	private String value;
	private String key_;

	protected FormElement() {
	}

	public FormElement(FormElementVo vo) {
		name = vo.getName();
		type = vo.getType();
		value = vo.getValue();
		key_ = vo.getKey_();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormElementType getType() {
		return type;
	}

	public void setType(FormElementType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey_() {
		return key_;
	}

	public void setKey_(String key_) {
		this.key_ = key_;
	}

}

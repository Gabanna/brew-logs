package de.rgse.brewlogs.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class FormElementVo {

	@JsonProperty
	private String name;

	@JsonProperty
	private FormElementType type;

	@JsonProperty
	private boolean required;

	@JsonProperty
	private String value;

	@JsonProperty
	private String key_;

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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
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

	public static FormElementVo getComment() {
		FormElementVo elementVo = new FormElementVo();
		
		elementVo.setKey_("comment");
		elementVo.setName("Kommentar");
		elementVo.setRequired(false);
		elementVo.setType(FormElementType.String);
		
		return elementVo;
	}
	
	public enum FormElementType {
		String, Number, Boolean, Time;
	}
}

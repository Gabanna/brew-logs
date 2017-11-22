package de.rgse.brewlog.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@SuppressWarnings("serial")
public class QuantifyableItem extends BaseDomain<Long> {

	private String name;

	@Min(0)
	private Float quantity;

	@NotNull
	@Enumerated(EnumType.STRING)
	private QuantifyableItemType itemType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public QuantifyableItemType getItemType() {
		return itemType;
	}

	public void setItemType(QuantifyableItemType itemType) {
		this.itemType = itemType;
	}

}
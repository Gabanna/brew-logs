package de.rgse.brewlogs.converter;

import java.time.Instant;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class InstantConverter implements AttributeConverter<Instant, Date> {

	@Override
	public Date convertToDatabaseColumn(Instant attribute) {
		return attribute != null ? new Date(attribute.toEpochMilli()) : null;
	}

	@Override
	public Instant convertToEntityAttribute(Date dbData) {
		return dbData != null ? Instant.ofEpochMilli(dbData.getTime()) : null;
	}

}

package de.rgse.brewlog.converter;

import java.time.Duration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, String> {

	@Override
	public String convertToDatabaseColumn(Duration attribute) {
		return attribute == null ? null : attribute.toString();
	}

	@Override
	public Duration convertToEntityAttribute(String column) {
		return column == null ? null : Duration.parse(column);
	}

}

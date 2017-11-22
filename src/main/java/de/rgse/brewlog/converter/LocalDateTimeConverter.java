package de.rgse.brewlog.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

	@Override
	public String convertToDatabaseColumn(LocalDateTime localDateTime) {
		return localDateTime == null ? null : DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String string) {
		return string == null ? null : LocalDateTime.parse(string, DateTimeFormatter.ISO_DATE_TIME);
	}

}

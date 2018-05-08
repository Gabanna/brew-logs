package de.rgse.brewlogs.domain.converters;

import javax.persistence.AttributeConverter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        Date result = null;

        if (null != attribute) {
            result = Date.from(attribute.atZone(ZoneId.systemDefault()).toInstant());
        }

        return result;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        LocalDateTime result = null;

        if (null != dbData) {
            result = LocalDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
        }

        return result;
    }
}

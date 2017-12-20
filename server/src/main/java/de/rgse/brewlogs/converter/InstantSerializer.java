package de.rgse.brewlogs.converter;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class InstantSerializer extends StdSerializer<Instant> {

	public InstantSerializer() {
		super(Instant.class, false);
	}
	
	protected InstantSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	protected InstantSerializer(JavaType type) {
		super(type);
	}

	@Override
	public void serialize(Instant value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeNumber(value.toEpochMilli());
	}

}

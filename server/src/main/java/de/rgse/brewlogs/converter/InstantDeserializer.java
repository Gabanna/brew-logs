package de.rgse.brewlogs.converter;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class InstantDeserializer extends StdDeserializer<Instant>{

	public InstantDeserializer() {
		super(Instant.class);
	}
	
	protected InstantDeserializer(Class<?> vc) {
		super(vc);
	}
	
    protected InstantDeserializer(JavaType valueType) {
        super(valueType);
    }

	@Override
	public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String value = p.getText().trim();
		return Instant.ofEpochMilli(Long.valueOf(value));
	}

}

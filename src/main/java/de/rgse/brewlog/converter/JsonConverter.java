package de.rgse.brewlog.converter;

import java.util.function.Function;

import com.google.gson.Gson;

public class JsonConverter {
	
	public <T> Function<T, String> convertToJson() {
		return o -> {
			return new Gson().toJson(o);
		};
	}

	public <T> Function<String, T> convertFromJson(Class<T> targetClazz) {
		return json -> {
			return new Gson().fromJson(json, targetClazz);
		};
	}
}

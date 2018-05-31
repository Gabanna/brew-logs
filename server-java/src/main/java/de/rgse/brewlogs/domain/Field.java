package de.rgse.brewlogs.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.JsonObject;
import javax.json.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Field {

    @JsonProperty
    private String type;

    @JsonProperty
    private String name;

    @JsonProperty
    private String ref;

    @JsonProperty
    private Object value;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public static Field of(JsonValue jsonValue) {
        JsonObject jsonObject = (JsonObject) jsonValue;

        Field field = new Field();

        field.type = jsonObject.getString("type");
        field.name = jsonObject.getString("name");
        field.ref = jsonObject.containsKey("ref") ? jsonObject.getString("ref") : null;

        return field;
    }
}

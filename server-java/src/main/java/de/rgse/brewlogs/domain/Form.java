package de.rgse.brewlogs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Form {

    @JsonProperty
    private List<Field> fields = Collections.emptyList();

    public static Form of(JsonObject jsonObject) {
        Form form = null;

        if(jsonObject != null) {
            form = new Form();
            JsonArray jsonarray = jsonObject.getJsonArray("fields");

            form.fields = jsonObject.getJsonArray("fields").stream().map(Field::of).collect(Collectors.toList());
        }

        return form;
    }
}

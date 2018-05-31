package de.rgse.brewlogs.services;

import de.rgse.brewlogs.api.vo.TaskVo;
import de.rgse.brewlogs.domain.Form;
import org.camunda.bpm.engine.task.Task;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormResolver {

    @Inject
    private Logger logger;

    public Form readForm(Task task) {
        Form form = null;

        String file = String.format("/forms/%s.json", task.getTaskDefinitionKey());

        try (InputStream in = TaskVo.class.getResourceAsStream(file)) {
            if(in != null) {
                try(JsonReader reader = Json.createReader(in)) {
                    JsonObject jsonObject = reader.readObject();
                    form = Form.of(jsonObject);
                }
            } else {
                logger.log(Level.SEVERE, "unable to read form definition {0}. File not found", file);
            }
        } catch(IOException e) {
            logger.log(Level.SEVERE, "unable to read form definition " + file, e);
        }

        return form;
    }
}

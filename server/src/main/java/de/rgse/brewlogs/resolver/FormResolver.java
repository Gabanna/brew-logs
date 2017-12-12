package de.rgse.brewlogs.resolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import de.rgse.brewlogs.vo.FormElementVo;

@Component
public class FormResolver {

	public List<FormElementVo> getForm(String formKey) {
		List<FormElementVo> result = new LinkedList<>();
		try(InputStream stream = getClass().getResourceAsStream(formKey)) {
			result = new Gson().fromJson(new InputStreamReader(stream), List.class);
	
		}catch(IOException exception) {
			exception.printStackTrace();
		}
		return result;
	}
}

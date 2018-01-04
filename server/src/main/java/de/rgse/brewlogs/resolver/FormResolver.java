package de.rgse.brewlogs.resolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import de.rgse.brewlogs.vo.FormElementVo;

@Component
public class FormResolver {

	@Autowired
	private FormService formService;
	
	@SuppressWarnings("unchecked")
	public List<FormElementVo> getForm(String taskId) {
		String taskFormData = formService.getTaskFormData(taskId).getFormKey();
		
		List<FormElementVo> result = new LinkedList<>();
		
		if(null != taskFormData) {
			try(InputStream stream = getClass().getResourceAsStream(taskFormData)) {
				result = new Gson().fromJson(new InputStreamReader(stream), List.class);
				
			}catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		
		return result;
	}
}

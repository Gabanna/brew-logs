package de.rgse.brewlog.view.validation;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import de.rgse.brewlog.repository.UserRepository;
import de.rgse.brewlog.repository.specifications.UserNameCountSpecification;

@Named
@RequestScoped
public class UsernameValidator implements Validator {

	@Inject
	private UserRepository userRepository;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null && !value.toString().isEmpty()) {
			List<Long> userNameCount = userRepository.query(new UserNameCountSpecification(value.toString()));
			
			if(userNameCount.get(0) != 0) {
				throw new ValidatorException(new FacesMessage("the username is already taken"));
			}
			
		} else {
			throw new ValidatorException(new FacesMessage("the username is required"));
		}
		
	}

}

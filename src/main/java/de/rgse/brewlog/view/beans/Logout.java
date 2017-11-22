package de.rgse.brewlog.view.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;

import de.rgse.brewlog.decorators.Brew;

@Named
@RequestScoped
public class Logout {

	@Inject @Brew
	private Logger logger;
	
	public void submit() throws IOException {
		logger.info("logging out");
		
		if (SecurityUtils.getSubject().hasRole("root")) {
			final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			SecurityUtils.getSubject().logout();
			externalContext.invalidateSession(); // cleanup user related session state
			externalContext.redirect("login.xhtml?faces-redirect=true");
		}
	}
}

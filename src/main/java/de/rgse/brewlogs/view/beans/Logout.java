package de.rgse.brewlogs.view.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;

@Named
@RequestScoped
public class Logout {

	public void submit() throws IOException {
		if (SecurityUtils.getSubject().hasRole("root")) {
			final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			SecurityUtils.getSubject().logout();
			externalContext.invalidateSession(); // cleanup user related session state
			externalContext.redirect("login.xhtml?faces-redirect=true");
		}
	}
}

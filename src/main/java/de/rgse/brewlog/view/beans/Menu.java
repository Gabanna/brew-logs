package de.rgse.brewlog.view.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class Menu implements Serializable {

	private MenuModel model;
	
	@PostConstruct
	private void init() {
		model = new DefaultMenuModel();
		model.addElement(new DefaultMenuItem("Test"));
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

}

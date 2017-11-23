package de.rgse.brewlog.view.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.slf4j.Logger;

import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.auth.User;
import de.rgse.brewlog.repository.UserRepository;

@Named
@RequestScoped
public class Register {

	@Inject
	private UserRepository repository;
	
	@Inject @Brew
	private PasswordService passwordService;
	
	@Inject @Brew
	private Logger logger;
	
	private String userName;
	private String password;
	private String passwordRepeat;
	
	@Transactional
	public String submit() {
		User user = new User(userName, passwordService.encryptPassword(password));
		repository.add(user);
		logger.info("user {} added", userName);
		
		

		return "/login.xhtml?faces-redirect=true";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public void passwordsMatch() {
		
	}
}

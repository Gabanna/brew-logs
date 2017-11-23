package de.rgse.brewlog.domain.auth;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.infinispan.util.concurrent.ConcurrentHashSet;

import de.rgse.brewlog.domain.IdProvider;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "userName") })
public class User implements IdProvider<String> {

	@Id
	private String userName;

	private String password;

	@ManyToMany
	private Set<Role> roles;

	protected User() {
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
		roles = new ConcurrentHashSet<>();
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

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public String getId() {
		return userName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

}

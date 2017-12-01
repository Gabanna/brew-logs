package de.rgse.brewlog.domain.auth;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.infinispan.util.concurrent.ConcurrentHashSet;

import de.rgse.brewlog.domain.IdProvider;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String getId() {
		return userName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

	public JwtBuilder getJwt() {
		return Jwts.builder()
			.setIssuer(getClass().getCanonicalName())
			.setId(UUID.randomUUID().toString())
			.setIssuedAt(new Date())
			.setSubject(userName)
			.claim("permissions", roles);
	}

}

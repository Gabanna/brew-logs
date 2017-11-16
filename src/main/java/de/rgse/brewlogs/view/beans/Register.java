package de.rgse.brewlogs.view.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

@Named
@RequestScoped
public class Register {

	@PersistenceContext(unitName = "brew-logs-pu")
	private EntityManager entityManager;

	@Transactional
	public void registrate(Object object) {
		System.out.println(object);
	}

	private String generatePassword(String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than Hex):
		return new Sha256Hash(plainTextPassword, salt, 1024).toBase64();
	}
}

package de.rgse.brewlog.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.rgse.brewlog.decorators.Brew;

@Dependent
public class UtilProducer {

	@Produces @Brew
	public Logger produceLogger(InjectionPoint point) {
		return LoggerFactory.getLogger(point.getBean().getBeanClass());
	}
	
	@Produces @Brew
	private PasswordService producePasswordService() {
		return new PasswordMatcher().getPasswordService();
	}
}

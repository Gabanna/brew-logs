package de.rgse.brewlogs.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class LoggerProducer {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Logger produceLogger(InjectionPoint ip) {
		Class<?> loggingTarget = null;

		if (ip.getField() != null) {
			loggingTarget = ip.getField().getType();

		} else if (ip.getMember() != null) {
			loggingTarget = ip.getMember().getDeclaringClass();

		} else if (ip.getMethodParameter() != null) {
			loggingTarget = ip.getMethodParameter().getDeclaringClass();

		}

		return LoggerFactory.getLogger(loggingTarget);
	}
}

package de.rgse.brewlogs.logging;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;

@Configuration
public class LoggerConfig {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Logger createLogger(InjectionPoint ip) {
		
		Class<?> loggingTarget = null;
		Member member = ip.getMember();
		Field field = ip.getField();
		MethodParameter parameter = ip.getMethodParameter();
		
		if(member != null) {
			loggingTarget = member.getDeclaringClass();
		
		} else if(field != null) {
			loggingTarget = field.getDeclaringClass();
			
		} else if(parameter != null) {
			loggingTarget = parameter.getDeclaringClass();
			
		}
		
		return LoggerFactory.getLogger(loggingTarget);
	}
}

package de.rgse.brewlog.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.rgse.brewlog.decorators.Brew;

@Dependent
public class UtilProducer {

	@Produces @Brew
	public Logger produceLogger() {
		return LoggerFactory.getLogger(getClass());
	}
}

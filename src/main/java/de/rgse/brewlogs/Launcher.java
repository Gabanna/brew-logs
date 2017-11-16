package de.rgse.brewlogs;

import java.util.Map;

import org.wildfly.swarm.Swarm;

import de.rgse.brewlogs.configuration.CliParser;
import de.rgse.brewlogs.configuration.YamlConfigProducer;

public class Launcher  {

	public static void main(String[] args) throws Exception {
		Map<String, String> properties = CliParser.parseToProperties(args);
		properties.forEach((key, value) -> System.setProperty(key, value));
		
		new YamlConfigProducer().getConfiguration().getDataSourceConfig().writeToSystem();
		
		Swarm.main(args);
	}
}

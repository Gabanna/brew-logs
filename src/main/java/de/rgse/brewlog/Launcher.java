package de.rgse.brewlog;

import java.util.Map;

import org.wildfly.swarm.Swarm;

import de.rgse.brewlog.configuration.CliParser;
import de.rgse.brewlog.configuration.YamlConfigProducer;

public class Launcher  {

	public static void main(String[] args) throws Exception {
		Map<String, String> properties = CliParser.parseToProperties(args);
		properties.forEach((key, value) -> System.setProperty(key, value));
		
		new YamlConfigProducer().getConfiguration().getDataSourceConfig().writeToSystem();
		
		Swarm.main(args);
	}
}

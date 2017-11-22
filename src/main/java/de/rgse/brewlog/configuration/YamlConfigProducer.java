package de.rgse.brewlog.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.yaml.snakeyaml.Yaml;

import de.rgse.brewlog.decorators.Brew;

@ApplicationScoped
public class YamlConfigProducer {

	private static Configuration configuration;

	@Brew
	@Produces
	public Configuration getConfiguration() {
		try {
			if (null == configuration) {
				init();
			}
			return configuration.clone();

		} catch (CloneNotSupportedException | IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Brew
	@Produces
	public DataSourceConfig dataSourceConfig() {
		return getConfiguration().getDataSourceConfig();
	}

	private static void init() throws IOException {
		String file = System.getProperty("brew.file");
		if (null != file) {
			Yaml yaml = new Yaml();
			try (InputStream in = Files.newInputStream(Paths.get(file))) {
				if (in != null) {
					configuration = yaml.loadAs(in, Configuration.class);
				} else {
					throw new IllegalStateException(String.format("the configuration file \"%s\" could not be found", file));
				}
			}
		} else {
			throw new IllegalStateException("property \"file\" is mandatory");
			
		}
	}
}
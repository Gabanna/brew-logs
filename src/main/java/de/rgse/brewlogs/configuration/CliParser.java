package de.rgse.brewlogs.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliParser {

	public static Map<String, String> parseToProperties(String[] args) throws ParseException {
		Options options = new Options();
		options.addOption(new Option("f", "file", true, "the location of the config file"));

		CommandLine parse = new DefaultParser().parse(options, args);
		Map<String, String> result = new HashMap<>();

		Arrays.stream(parse.getOptions())
				.forEach(option -> result.put("brew." + option.getLongOpt(), option.getValue()));

		return result;
	}
}

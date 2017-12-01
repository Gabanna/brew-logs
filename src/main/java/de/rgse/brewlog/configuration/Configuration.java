package de.rgse.brewlog.configuration;

public class Configuration implements Cloneable {

	private DataSourceConfig dataSource;

	private String apiKey;

	public DataSourceConfig getDataSourceConfig() {
		return dataSource;
	}

	public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
		this.dataSource = dataSourceConfig;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public Configuration clone() throws CloneNotSupportedException {
		return (Configuration) super.clone();
	}
}

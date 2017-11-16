package de.rgse.brewlogs.configuration;

public class Configuration implements Cloneable {

	private DataSourceConfig dataSource;

	public DataSourceConfig getDataSourceConfig() {
		return dataSource;
	}

	public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
		this.dataSource = dataSourceConfig;
	}

	@Override
	public String toString() {
		return "Configuration [dataSource=" + dataSource + "]";
	}

	@Override
	public Configuration clone() throws CloneNotSupportedException {
		return (Configuration) super.clone();
	}
}

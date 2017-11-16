package de.rgse.brewlogs.configuration;

public class DataSourceConfig implements Cloneable {

	private String driverName;
	private String jndiName;
	private boolean jta;
	private boolean spy;
	private String connectionUrl;
	private String userName;
	private String password;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public boolean isJta() {
		return jta;
	}

	public void setJta(boolean jta) {
		this.jta = jta;
	}

	public boolean isSpy() {
		return spy;
	}

	public void setSpy(boolean spy) {
		this.spy = spy;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void writeToSystem() {
		String prefix = "swarm.datasources.data-sources.ExampleDS.";
		System.setProperty(prefix + "driver-name", driverName);
		System.setProperty(prefix + "jndi-name", jndiName);
		System.setProperty(prefix + "connection-url", connectionUrl);
		System.setProperty(prefix + "user-name", userName);
		System.setProperty(prefix + "password", password);
		System.setProperty(prefix + "jta", Boolean.toString(jta));
		System.setProperty(prefix + "spy", Boolean.toString(spy));
	}

	@Override
	public DataSourceConfig clone() throws CloneNotSupportedException {
		return (DataSourceConfig) super.clone();
	}
	
	@Override
	public String toString() {
		return "DataSource [driverName=" + driverName + ", jndiName=" + jndiName + ", jta=" + jta + ", spy=" + spy
				+ ", connectionUrl=" + connectionUrl + ", username=" + userName + ", password=" + password + "]";
	}

}

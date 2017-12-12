package de.rgse.brewlogs.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties("spring.dataSource")
public class PersistenceConfig {

	private String driverClassName;
	private String url;
	private String userName;
	private String password;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(driverClassName);
		dataSourceConfig.setJdbcUrl(url);
		dataSourceConfig.setUsername(userName);
		dataSourceConfig.setPassword(password);

		return new HikariDataSource(dataSourceConfig);
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

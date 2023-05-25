package com.astralbrands.flight.x3.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Value("${x3.database.url}")
	private String x3DatabaseUrl;
	
	@Bean(name = "x3DataSource")
	public DataSource x3DataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(x3DatabaseUrl);
		return dataSourceBuilder.build();
	}

}

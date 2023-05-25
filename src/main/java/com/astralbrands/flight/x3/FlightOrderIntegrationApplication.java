package com.astralbrands.flight.x3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FlightOrderIntegrationApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FlightOrderIntegrationApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(FlightOrderIntegrationApplication.class, args);
	}

}

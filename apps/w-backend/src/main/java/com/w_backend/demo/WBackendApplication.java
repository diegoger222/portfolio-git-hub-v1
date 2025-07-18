package com.w_backend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WBackendApplication {
	public static final String DEV_PROFILE = "dev";
	public static final String PROD_PROFILE = "prod";
	public static final String TEST_PROFILE = "test";
	public static final String DEFAULT_PROFILE = DEV_PROFILE;

	public static void main(String[] args) {
		SpringApplication.run(WBackendApplication.class, args);

	}

}

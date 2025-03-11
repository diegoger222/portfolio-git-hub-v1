package com.w_backend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WBackendApplication {
	public static final String DEV_PROFILE = "dev";

	public static void main(String[] args) {
		SpringApplication.run(WBackendApplication.class, args);

	}

}

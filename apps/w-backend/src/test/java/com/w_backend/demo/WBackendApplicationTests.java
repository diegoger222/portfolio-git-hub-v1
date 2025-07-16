package com.w_backend.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.w_backend.demo.WBackendApplication.TEST_PROFILE;

@SpringBootTest
@ActiveProfiles(TEST_PROFILE)
class WBackendApplicationTests {
	// TODO add tests for the application
	// and check kafka config test profile
	@Test
	void contextLoads() {
		// This test is intentionally left empty to verify that the Spring application
		// context loads successfully.
	}

}

package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:tc:postgresql:16-alpine:///databasename"
})
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

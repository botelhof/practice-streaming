package com.practice.streaming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestcontainersConfiguration.class)
class StreamingApplicationTests {

	@Test
	void contextLoads() {
		// This will now use the Testcontainers Cassandra instance
	}
}

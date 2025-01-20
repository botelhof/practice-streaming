package com.practice.streaming;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public abstract class TestcontainersConfiguration {

	@Container
	static final CassandraContainer<?> cassandraContainer = new CassandraContainer<>("cassandra:4.0")
			.withExposedPorts(9042)
			.withEnv("CASSANDRA_DC", "datacenter1");

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext context) {
			TestPropertyValues.of(
					"spring.data.cassandra.contact-points=" + cassandraContainer.getHost() + ":" + cassandraContainer.getMappedPort(9042),
					"spring.data.cassandra.local-datacenter=datacenter1"
			).applyTo(context.getEnvironment());
		}
	}
}

package com.practice.streaming;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.datastax.oss.driver.api.core.CqlSession;

import java.net.InetSocketAddress;

@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = TestcontainersConfiguration.Initializer.class)
public class TestcontainersConfiguration {

	@SuppressWarnings({"deprecation", "resource"})
	@Container
	static final CassandraContainer<?> cassandraContainer = new CassandraContainer<>(
			DockerImageName.parse("cassandra:4.0")
	)
			.withExposedPorts(9042)
			.withEnv("CASSANDRA_DC", "datacenter1");

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext context) {
			TestPropertyValues.of(
					"spring.data.cassandra.contact-points=" + cassandraContainer.getHost(),
					"spring.data.cassandra.port=" + cassandraContainer.getMappedPort(9042),
					"spring.data.cassandra.local-datacenter=datacenter1",
					"spring.data.cassandra.keyspace=your_keyspace_name"
			).applyTo(context.getEnvironment());

			// Create keyspace if needed
			try (CqlSession session = CqlSession.builder()
					.addContactPoint(new InetSocketAddress(cassandraContainer.getHost(), cassandraContainer.getMappedPort(9042)))
					.withLocalDatacenter("datacenter1")
					.build()) {
				session.execute("CREATE KEYSPACE IF NOT EXISTS your_keyspace_name WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}");
			}
		}
	}
}

package com.practice.streaming.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter playableCreatedCounter(MeterRegistry registry) {
        return Counter.builder("playable.created")
                .description("Number of playables created")
                .register(registry);
    }

    @Bean
    public Counter playableViewedCounter(MeterRegistry registry) {
        return Counter.builder("playable.viewed")
                .description("Number of times playable was viewed")
                .register(registry);
    }

}

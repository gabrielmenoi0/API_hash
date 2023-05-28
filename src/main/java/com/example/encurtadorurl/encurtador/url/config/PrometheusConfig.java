//package com.example.encurtadorurl.encurtador.url.config;
//
//import io.micrometer.prometheus.PrometheusMeterRegistry;
//import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PrometheusConfig {
//    @Bean
//    public PrometheusMeterRegistry prometheusMeterRegistry() {
//        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT_REGISTRY);
//    }
//
//    @Bean
//    public MeterRegistryCustomizer<PrometheusMeterRegistry> metricsCommonTags() {
//        return registry -> registry.config().commonTags("application", "your-application-name");
//    }
//}

//package com.example.encurtadorurl.encurtador.url.config;
//
//import io.prometheus.client.spring.boot.PrometheusMvcEndpoint;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//
//@Configuration
//public class ActuatorConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/actuator/prometheus", "/prometheus");
//    }
//
////    @Bean
////    public PrometheusMvcEndpoint prometheusMvcEndpoint() {
////        return new PrometheusMvcEndpoint();
////    }
//}
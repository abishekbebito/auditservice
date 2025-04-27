package com.camunda.auditTrail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
//import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient webClient() {
        return RestClient.builder().build();
    }
}

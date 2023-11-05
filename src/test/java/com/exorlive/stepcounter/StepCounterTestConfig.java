package com.exorlive.stepcounter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StepCounterTestConfig {
    @Bean
    @Primary
    public TestRestTemplate testRestTemplate(@Value("${server.port}") int port) {
        RestTemplateBuilder builder = new RestTemplateBuilder().rootUri("http://localhost:" + port);
        return new TestRestTemplate(builder);
    }
}

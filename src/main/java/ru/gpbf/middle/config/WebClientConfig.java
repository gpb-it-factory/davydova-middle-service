package ru.gpbf.middle.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder()
                .errorHandler(new WebClientErrorHandler())
                .build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.setConnectTimeout(Duration.ofMillis(9000));
        restTemplateBuilder.setReadTimeout(Duration.ofMillis(9000));
        return restTemplateBuilder;
    }

    @Bean
    public RestClient restClient() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(Duration.ofMillis(9000));
        clientHttpRequestFactory.setReadTimeout(Duration.ofMillis(9000));
        return RestClient.builder()
                .requestFactory(clientHttpRequestFactory)
                .build();
    }

}

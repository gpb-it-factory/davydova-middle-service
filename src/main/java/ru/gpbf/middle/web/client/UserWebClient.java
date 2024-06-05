package ru.gpbf.middle.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.config.WebClientErrorHandler;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.entity.UserEntity;
import ru.gpbf.middle.exception.ABSServerException;

import java.net.URI;
import java.util.Optional;

@Service
public class UserWebClient {
    private static final Logger log = LoggerFactory.getLogger(UserWebClient.class);
    private final RestTemplate restTemplate;
    private final Environment environment;


    public UserWebClient(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public Optional<ErrorEntity> register(Long userId) {
        URI uri = URI.create(environment.getProperty("abs.url") + environment.getProperty("abs.path.register"));
        ResponseEntity<ErrorEntity> result = restTemplate.postForEntity(uri, new UserEntity(userId), ErrorEntity.class);
        if (result.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return Optional.empty();
        } else if (result.hasBody() && result.getBody() != null) {
            return Optional.of(result.getBody());
        } else {
            log.error("Error registering user: {}", result.getBody());
            throw new ABSServerException("Exception on abs server, server is not available");
        }
    }
}

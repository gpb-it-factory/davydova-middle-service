package ru.gpbf.middle.web.client;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.entity.UserEntity;
import ru.gpbf.middle.exception.ABSServerException;

import java.util.Optional;

@Service
public class UserWebClient {
    private final WebClient webClient;
    private final Environment environment;

    public UserWebClient(WebClient webClient, Environment environment) {
        this.webClient = webClient;
        this.environment = environment;
    }

    public Optional<ErrorEntity> register(Long userTelegramId) {
        return webClient.post().uri(builder -> builder
                        .scheme(environment.getProperty("abs.scheme"))
                        .host(environment.getProperty("abs.url"))
                        .port(environment.getProperty("abs.port"))
                        .path(environment.getProperty("abs.path.register"))
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new UserEntity(userTelegramId)), UserEntity.class)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.CREATED)) {
                        return Mono.empty();
                    } else if (response.statusCode().is5xxServerError()) {
                        throw new ABSServerException("Exception on abs server, server is not available");
                    } else {
                        return response.bodyToMono(ErrorEntity.class);
                    }
                }).blockOptional();
    }
}

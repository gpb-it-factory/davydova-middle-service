package ru.gpbf.middle.web.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.entity.UserEntity;
import ru.gpbf.middle.web.client.properties.ABSProperties;

import java.net.URI;

@Service
public class UserWebClient {
    private final RestTemplate restTemplate;
    private final ABSProperties absProperties;


    public UserWebClient(RestTemplate restTemplate, ABSProperties absProperties) {
        this.restTemplate = restTemplate;
        this.absProperties = absProperties;
    }

    public void register(User user) {
        URI uri = URI.create(absProperties.url() + absProperties.register());
        ResponseEntity<ErrorEntity> result = restTemplate.postForEntity(uri, new UserEntity(user.getUserTelegramId(), user.getUserName()), ErrorEntity.class);
        ErrorEntityHandler.handle(result);
    }
}

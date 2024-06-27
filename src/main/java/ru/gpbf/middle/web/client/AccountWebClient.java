package ru.gpbf.middle.web.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.entity.AccountEntity;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.web.client.properties.ABSProperties;

import java.net.URI;

@Service
public class AccountWebClient {

    private final RestTemplate restTemplate;
    private final ABSProperties absProperties;

    public AccountWebClient(RestTemplate restTemplate, ABSProperties absProperties) {
        this.restTemplate = restTemplate;
        this.absProperties = absProperties;
    }

    public void register(AccountRegister accountRegister) {
        URI uri = URI.create(absProperties.url() + absProperties.register() + "/" + accountRegister.getUserTelegramId() + absProperties.account());
        ResponseEntity<ErrorEntity> result = restTemplate.postForEntity(uri, new AccountEntity(accountRegister.getName()), ErrorEntity.class);
        ErrorEntityHandler.handle(result);
    }
}

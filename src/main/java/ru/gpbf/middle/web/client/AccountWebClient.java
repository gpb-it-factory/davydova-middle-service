package ru.gpbf.middle.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.entity.AccountEntity;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.exception.ABSServerException;

import java.net.URI;
import java.util.Optional;

@Service
public class AccountWebClient {
    private static final Logger log = LoggerFactory.getLogger(AccountWebClient.class);
    private final RestTemplate restTemplate;
    private final Environment environment;

    public AccountWebClient(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public Optional<ErrorEntity> register(AccountRegister accountRegister) {
        URI uri = URI.create(environment.getProperty("abs.url") + environment.getProperty("abs.path.register") + "/" + accountRegister.getUserTelegramId() + environment.getProperty("abs.path.account"));
        ResponseEntity<ErrorEntity> result = restTemplate.postForEntity(uri, new AccountEntity(accountRegister.getName()), ErrorEntity.class);
        if (result.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return Optional.empty();
        } else if (result.hasBody() && result.getBody() != null) {
            return Optional.of(result.getBody());
        } else {
            log.error("Error creating account: {}", result.getBody());
            throw new ABSServerException("Exception on abs server, server is not available");
        }
    }
}

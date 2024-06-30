package ru.gpbf.middle.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.entity.AccountEntity;
import ru.gpbf.middle.entity.AccountRegisterEntity;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;
import ru.gpbf.middle.web.client.properties.ABSProperties;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class AccountWebClient {
    private static final Logger log = LoggerFactory.getLogger(AccountWebClient.class);
    private final RestClient restClient;
    private final RestTemplate restTemplate;
    private final ABSProperties absProperties;

    public AccountWebClient(RestClient restClient, RestTemplate restTemplate, ABSProperties absProperties) {
        this.restClient = restClient;
        this.restTemplate = restTemplate;
        this.absProperties = absProperties;
    }

    public void register(AccountRegister accountRegister) {
        URI uri = URI.create(absProperties.url() + absProperties.register() + "/" + accountRegister.getUserTelegramId() + absProperties.account());
        ResponseEntity<ErrorEntity> result = restTemplate.postForEntity(uri, new AccountRegisterEntity(accountRegister.getName()), ErrorEntity.class);
        ErrorEntityHandler.handle(result);
    }

    public List<AccountEntity> getAccounts(Long userTelegramId) {
        URI uri = URI.create(absProperties.url() + absProperties.register() + "/" + userTelegramId + absProperties.account());

        return restClient.get().uri(uri).accept(APPLICATION_JSON).exchange((clientRequest, clientResponse) -> {
            if (clientResponse.getStatusCode() == HttpStatus.OK) {
                return Objects.requireNonNull(clientResponse.bodyTo(new ParameterizedTypeReference<>() {
                }));
            } else {
                ErrorEntity errorEntity = clientResponse.bodyTo(ErrorEntity.class);
                log.warn("user does not get balance with error {}", errorEntity);

                if (clientResponse.getStatusCode() == HttpStatus.CONFLICT && errorEntity != null) {
                    throw new ConflictServerException("User does not exist", errorEntity.getTraceId());
                } else if (errorEntity != null) {
                    throw new ABSServerException(errorEntity.getMessage(), errorEntity.getTraceId(), errorEntity.getCode());

                } else {
                    throw new ABSServerException("Abs server exception");
                }
            }
        });
    }
}

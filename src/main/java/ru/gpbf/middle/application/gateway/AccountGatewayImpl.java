package ru.gpbf.middle.application.gateway;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.exception.ABSRequestError;
import ru.gpbf.middle.web.client.AccountWebClient;

import java.util.Optional;

@Service
public class AccountGatewayImpl implements AccountGateway {
    private static final Logger log = LoggerFactory.getLogger(AccountGatewayImpl.class);
    private final AccountWebClient accountWebClient;
    private final ModelMapper mapper;

    public AccountGatewayImpl(AccountWebClient accountWebClient, ModelMapper mapper) {
        this.accountWebClient = accountWebClient;
        this.mapper = mapper;
    }

    @Override
    public Optional<ABSRequestError> register(AccountRegister accountRegister) {
        Optional<ErrorEntity> response = accountWebClient.register(accountRegister);

        if (response.isPresent()) {
            log.error(String.valueOf(response.get()));
            ABSRequestError error = mapper.map(response.get(), ABSRequestError.class);
            return Optional.of(error);
        }
        return Optional.empty();
    }
}

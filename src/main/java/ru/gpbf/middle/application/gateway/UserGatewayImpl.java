package ru.gpbf.middle.application.gateway;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.exception.ABSRequestError;
import ru.gpbf.middle.web.client.UserWebClient;

import java.util.Optional;

@Service
public class UserGatewayImpl implements UserGateway {
    private static final Logger log = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final UserWebClient userWebClient;
    private final ModelMapper mapper;

    public UserGatewayImpl(UserWebClient userWebClient, ModelMapper mapper) {
        this.userWebClient = userWebClient;
        this.mapper = mapper;
    }

    @Override
    public Optional<ABSRequestError> register(User user) {
        Optional<ErrorEntity> response = userWebClient.register(user);
        if (response.isPresent()) {
            log.error(String.valueOf(response.get()));
            return Optional.of(mapper.map(response.get(), ABSRequestError.class));
        }
        return Optional.empty();
    }
}

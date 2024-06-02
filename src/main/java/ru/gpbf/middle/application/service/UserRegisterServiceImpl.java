package ru.gpbf.middle.application.service;

import org.springframework.stereotype.Service;
import ru.gpbf.middle.application.gateway.UserGateway;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    private final UserGateway userGateway;

    public UserRegisterServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Optional<ABSRequestError> register(Long userTelegramId) {
        if (userTelegramId == null) {
            throw new RuntimeException("userTelegramId can not be null");
        }
        return userGateway.register(userTelegramId);
    }
}

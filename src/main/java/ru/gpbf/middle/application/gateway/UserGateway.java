package ru.gpbf.middle.application.gateway;

import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

public interface UserGateway {
    Optional<ABSRequestError> register(User user);
}

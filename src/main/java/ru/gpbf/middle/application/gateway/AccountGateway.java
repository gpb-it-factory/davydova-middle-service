package ru.gpbf.middle.application.gateway;

import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

public interface AccountGateway {
    Optional<ABSRequestError> register(AccountRegister accountRegister);
}

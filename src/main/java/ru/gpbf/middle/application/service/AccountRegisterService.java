package ru.gpbf.middle.application.service;

import ru.gpbf.middle.dto.CreateAccountRequest;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

public interface AccountRegisterService {
    Optional<ABSRequestError> register(CreateAccountRequest createAccountRequest);
}

package ru.gpbf.middle.application.service;

import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;


public interface UserRegisterService {
    Optional<ABSRequestError> register(Long userTelegramId);
}

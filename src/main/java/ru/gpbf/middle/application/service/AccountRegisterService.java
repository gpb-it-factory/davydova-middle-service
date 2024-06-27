package ru.gpbf.middle.application.service;

import ru.gpbf.middle.dto.CreateAccountRequest;

public interface AccountRegisterService {
    void register(CreateAccountRequest createAccountRequest);
}

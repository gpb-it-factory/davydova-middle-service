package ru.gpbf.middle.application.service;

import ru.gpbf.middle.dto.CreateUserRequest;

public interface UserRegisterService {
    void register(CreateUserRequest createUserRequest);
}

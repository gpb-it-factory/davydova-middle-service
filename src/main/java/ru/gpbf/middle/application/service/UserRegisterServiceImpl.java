package ru.gpbf.middle.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.gpbf.middle.application.gateway.UserGateway;
import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.dto.CreateUserRequestClient;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    private final ModelMapper modelMapper;
    private final UserGateway userGateway;

    public UserRegisterServiceImpl(ModelMapper modelMapper, UserGateway userGateway) {
        this.modelMapper = modelMapper;
        this.userGateway = userGateway;
    }

    @Override
    public Optional<ABSRequestError> register(CreateUserRequestClient createUserRequestClient) {
        User user = modelMapper.map(createUserRequestClient, User.class);
        return userGateway.register(user);
    }
}

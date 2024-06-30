package ru.gpbf.middle.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import ru.gpbf.middle.UserData;
import ru.gpbf.middle.application.gateway.UserGateway;
import ru.gpbf.middle.dto.CreateUserRequest;
import ru.gpbf.middle.exception.BadRequest;


class UserRegisterServiceImplTest {


    @Test
    void registerSuccess() {
        UserRegisterService userRegisterService = new UserRegisterServiceImpl(Mockito.mock(ModelMapper.class), Mockito.mock(UserGateway.class));

        Assertions.assertDoesNotThrow(() -> userRegisterService.register(UserData.CREATE_USER_REQUEST_CLIENT));
    }

    @Test
    void registerUnSuccessUserIdNull() {
        UserRegisterService userRegisterService = new UserRegisterServiceImpl(Mockito.mock(ModelMapper.class), Mockito.mock(UserGateway.class));

        Assertions.assertThrows(BadRequest.class, ()-> userRegisterService.register(new CreateUserRequest(null, UserData.USER_NAME)));



    }

    @Test
    void registerUnSuccessNameBlank() {
        UserRegisterService userRegisterService = new UserRegisterServiceImpl(Mockito.mock(ModelMapper.class), Mockito.mock(UserGateway.class));

        Assertions.assertThrows(BadRequest.class, ()-> userRegisterService.register(new CreateUserRequest(UserData.USER_ID, "")));

    }

    @Test
    void registerUnSuccessNameNull() {
        UserRegisterService userRegisterService = new UserRegisterServiceImpl(Mockito.mock(ModelMapper.class), Mockito.mock(UserGateway.class));

        Assertions.assertThrows(BadRequest.class, ()-> userRegisterService.register(new CreateUserRequest(UserData.USER_ID, null)));

    }
}
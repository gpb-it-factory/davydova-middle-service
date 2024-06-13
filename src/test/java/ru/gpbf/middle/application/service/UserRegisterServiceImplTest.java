package ru.gpbf.middle.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.UserData;


@SpringBootTest
class UserRegisterServiceImplTest extends AbstractMockWebServerTest {
    @Autowired
    private UserRegisterServiceImpl userRegisterService;

    @Test
    void registerSuccess() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Assertions.assertTrue(userRegisterService.register(UserData.createUserRequestClient).isEmpty());
    }

    @Test
    void registerUnSuccess() {
        MockWebServerUtil.runWithBody400(mockWebServer);

        Assertions.assertTrue(userRegisterService.register(UserData.createUserRequestClient).isPresent());

    }
}
package ru.gpbf.middle.application.service;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.UserData;

import java.io.IOException;

@SpringBootTest
class UserRegisterServiceImplTest {
    @Autowired
    private UserRegisterServiceImpl userRegisterService;
    private MockWebServer mockWebServer;


    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start(6060);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void shutDownMockWebServer() {
        try {
            mockWebServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void registerSuccess() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Assertions.assertTrue(userRegisterService.register(UserData.userId).isEmpty());
    }

    @Test
    void registerUnSuccess() {
        MockWebServerUtil.runWithBody400(mockWebServer);

        Assertions.assertTrue(userRegisterService.register(UserData.userId).isPresent());

    }
}
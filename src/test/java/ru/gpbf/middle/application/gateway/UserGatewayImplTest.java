package ru.gpbf.middle.application.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.UserData;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.gpbf.middle.MockWebServerUtil.runWithBody400;


class UserGatewayImplTest extends AbstractMockWebServerTest {
    @Autowired
    UserGatewayImpl userGateway;


    @Test
    void registerSuccess() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Optional<ABSRequestError> absRequestError = userGateway.register(UserData.userId);
        assertTrue(absRequestError.isEmpty());
    }

    @Test
    void registerUnsuccessful() {
        runWithBody400(mockWebServer);

        Optional<ABSRequestError> absRequestError = userGateway.register(UserData.userId);
        assertTrue(absRequestError.isPresent());
    }

    @Test
    void registerIdNullExceptionNotThrow() {
        runWithBody400(mockWebServer);
        Optional<ABSRequestError> absRequestError = userGateway.register(null);
        assertTrue(absRequestError.isPresent());
    }
}
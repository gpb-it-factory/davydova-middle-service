package ru.gpbf.middle.application.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gpbf.middle.AbstractMockWebServerTest;
import ru.gpbf.middle.MockWebServerUtil;
import ru.gpbf.middle.UserData;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;

import static ru.gpbf.middle.MockWebServerUtil.runRegisterUserWithBody400;
import static ru.gpbf.middle.MockWebServerUtil.runRegisterUserWithBody409;


class UserGatewayImplTest extends AbstractMockWebServerTest {

    @Autowired
    UserGatewayImpl userGateway;


    @Test
    void registerSuccess() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Assertions.assertDoesNotThrow(() -> userGateway.register(UserData.USER));

    }

    @Test
    void registerUnsuccessfulConflict() {
        runRegisterUserWithBody409((mockWebServer));

        Assertions.assertThrows(ConflictServerException.class, () -> userGateway.register(UserData.USER));
    }

    @Test
    void registerUnsuccessfulUnknownCode() {
        runRegisterUserWithBody400((mockWebServer));

        Assertions.assertThrows(ABSServerException.class, () -> userGateway.register(UserData.USER));
    }

}
package ru.gpbf.middle.application.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gpbf.middle.*;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.gpbf.middle.MockWebServerUtil.runCreateAccountWithBody400;

class AccountGatewayImplTest extends AbstractMockWebServerTest {

    @Autowired
    AccountGatewayImpl accountGateway;

    @Test
    void registerSuccess() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Optional<ABSRequestError> absRequestError = accountGateway.register(AccountData.ACCOUNT_REGISTER);

        assertTrue(absRequestError.isEmpty());
    }

    @Test
    void registerUnsuccessful() {
        runCreateAccountWithBody400(mockWebServer);

        Optional<ABSRequestError> absRequestError = accountGateway.register(AccountData.ACCOUNT_REGISTER);

        assertTrue(absRequestError.isPresent());
    }
}
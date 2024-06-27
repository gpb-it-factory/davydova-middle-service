package ru.gpbf.middle.application.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gpbf.middle.*;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;

import static ru.gpbf.middle.MockWebServerUtil.runCreateAccountWithBody400;
import static ru.gpbf.middle.MockWebServerUtil.runCreateAccountWithBody409;

class AccountGatewayImplTest extends AbstractMockWebServerTest {

    @Autowired
    AccountGatewayImpl accountGateway;

    @Test
    void registerSuccess() {
        MockWebServerUtil.runEmptyBody204(mockWebServer);

        Assertions.assertDoesNotThrow(() -> accountGateway.register(AccountData.ACCOUNT_REGISTER));
    }

    @Test
    void registerUnsuccessfulConflict() {
        runCreateAccountWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> accountGateway.register(AccountData.ACCOUNT_REGISTER));

    }

    @Test
    void registerUnsuccessfulUnknownCode() {
        runCreateAccountWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> accountGateway.register(AccountData.ACCOUNT_REGISTER));

    }
}
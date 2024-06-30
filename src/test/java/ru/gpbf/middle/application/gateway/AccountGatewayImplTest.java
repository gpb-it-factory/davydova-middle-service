package ru.gpbf.middle.application.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gpbf.middle.*;
import ru.gpbf.middle.domain.Account;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;
import ru.gpbf.middle.exception.NotFoundException;

import static ru.gpbf.middle.MockWebServerUtil.*;

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
        runWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> accountGateway.register(AccountData.ACCOUNT_REGISTER));

    }

    @Test
    void registerUnsuccessfulUnknownCode() {
        runWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> accountGateway.register(AccountData.ACCOUNT_REGISTER));

    }

    @Test
    void getAccountsSuccess() {
        MockWebServerUtil.runAccountsBody200(mockWebServer);

        Account account = accountGateway.getAccount(UserData.USER_ID);

        Matcher.match(account, AccountData.ACCOUNT);
    }

    @Test
    void getAccountsUnsuccessfulNotFound() {
        runWithEmptyList200(mockWebServer);

        Assertions.assertThrows(NotFoundException.class, () -> accountGateway.getAccount(UserData.USER_ID));

    }

    @Test
    void getAccountsUnsuccessfulConflict() {
        runWithBody409(mockWebServer);

        Assertions.assertThrows(ConflictServerException.class, () -> accountGateway.getAccount(UserData.USER_ID));

    }

    @Test
    void getAccountsUnsuccessfulUnknownCode() {
        runWithBody400(mockWebServer);

        Assertions.assertThrows(ABSServerException.class, () -> accountGateway.getAccount(UserData.USER_ID));

    }
}
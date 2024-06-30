package ru.gpbf.middle.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gpbf.middle.AccountData;
import ru.gpbf.middle.application.gateway.AccountGateway;
import ru.gpbf.middle.application.gateway.AccountGatewayImpl;
import ru.gpbf.middle.exception.BadRequest;

class AccountRegisterServiceImplTest {

    @Test
    void registerSuccess() {
        AccountGateway accountGateway = Mockito.mock(AccountGatewayImpl.class);
        AccountRegisterService accountRegisterService = new AccountRegisterServiceImpl(accountGateway);

        Assertions.assertDoesNotThrow(() -> accountRegisterService.register(AccountData.CREATE_ACCOUNT_REQUEST_PROMOTION));

    }

    @Test
    void registerUnSuccess() {
        AccountGateway accountGateway = Mockito.mock(AccountGatewayImpl.class);
        AccountRegisterService accountRegisterService = new AccountRegisterServiceImpl(accountGateway);

        Assertions.assertThrows(BadRequest.class, () -> accountRegisterService.register(AccountData.CREATE_ACCOUNT_REQUEST_NOT_PROMOTION));
    }
}
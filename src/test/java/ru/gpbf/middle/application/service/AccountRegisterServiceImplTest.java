package ru.gpbf.middle.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gpbf.middle.AccountData;
import ru.gpbf.middle.application.gateway.AccountGateway;
import ru.gpbf.middle.application.gateway.AccountGatewayImpl;
import ru.gpbf.middle.exception.ABSRequestError;
import ru.gpbf.middle.exception.BadRequest;

import java.util.Optional;

class AccountRegisterServiceImplTest {

    @Test
    void registerSuccess() {
        AccountGateway accountGateway = Mockito.mock(AccountGatewayImpl.class);
        Mockito.when(accountGateway.register(Mockito.any())).thenReturn(Optional.empty());
        AccountRegisterService accountRegisterService = new AccountRegisterServiceImpl(accountGateway);

        Optional<ABSRequestError> result = accountRegisterService.register(AccountData.CREATE_ACCOUNT_REQUEST_PROMOTION);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void registerUnSuccess() {
        AccountGateway accountGateway = Mockito.mock(AccountGatewayImpl.class);
        Mockito.when(accountGateway.register(Mockito.any())).thenReturn(Optional.empty());
        AccountRegisterService accountRegisterService = new AccountRegisterServiceImpl(accountGateway);

        Assertions.assertThrows(BadRequest.class, () -> accountRegisterService.register(AccountData.CREATE_ACCOUNT_REQUEST_NOT_PROMOTION));
    }
}
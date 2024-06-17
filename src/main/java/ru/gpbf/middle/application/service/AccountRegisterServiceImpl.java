package ru.gpbf.middle.application.service;

import org.springframework.stereotype.Service;
import ru.gpbf.middle.application.gateway.AccountGateway;
import ru.gpbf.middle.domain.factory.AccountFactory;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.dto.CreateAccountRequest;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

@Service
public class AccountRegisterServiceImpl implements AccountRegisterService {
    private final AccountGateway accountGateway;

    public AccountRegisterServiceImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Optional<ABSRequestError> register(CreateAccountRequest createAccountRequest) {
        AccountRegister accountRegister = AccountFactory.getAccount(createAccountRequest.getUserTelegramId(), createAccountRequest.getAccountName());
        return accountGateway.register(accountRegister);
    }
}

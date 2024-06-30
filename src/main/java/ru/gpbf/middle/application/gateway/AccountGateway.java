package ru.gpbf.middle.application.gateway;

import ru.gpbf.middle.domain.Account;
import ru.gpbf.middle.domain.factory.AccountRegister;

public interface AccountGateway {
    void register(AccountRegister accountRegister);

    Account getAccount(Long userTelegramId);
}

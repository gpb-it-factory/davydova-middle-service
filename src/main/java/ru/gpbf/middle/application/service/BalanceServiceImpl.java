package ru.gpbf.middle.application.service;

import org.springframework.stereotype.Service;
import ru.gpbf.middle.application.gateway.AccountGateway;
import ru.gpbf.middle.domain.Account;

import java.math.BigDecimal;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final AccountGateway accountGateway;

    public BalanceServiceImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public BigDecimal getBalance(Long userTelegramId) {
        Account account = accountGateway.getAccount(userTelegramId);
        return account.getAmount();
    }
}

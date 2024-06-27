package ru.gpbf.middle.application.gateway;

import org.springframework.stereotype.Service;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.web.client.AccountWebClient;

@Service
public class AccountGatewayImpl implements AccountGateway {
    private final AccountWebClient accountWebClient;

    public AccountGatewayImpl(AccountWebClient accountWebClient) {
        this.accountWebClient = accountWebClient;
    }

    @Override
    public void register(AccountRegister accountRegister) {
        accountWebClient.register(accountRegister);
    }
}

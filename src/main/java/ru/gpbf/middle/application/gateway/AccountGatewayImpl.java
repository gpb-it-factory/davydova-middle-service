package ru.gpbf.middle.application.gateway;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.gpbf.middle.domain.Account;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.entity.AccountEntity;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.NotFoundException;
import ru.gpbf.middle.web.client.AccountWebClient;

import java.util.List;

@Service
public class AccountGatewayImpl implements AccountGateway {
    private final AccountWebClient accountWebClient;
    private final ModelMapper modelMapper;

    public AccountGatewayImpl(AccountWebClient accountWebClient, ModelMapper modelMapper) {
        this.accountWebClient = accountWebClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(AccountRegister accountRegister) {
        accountWebClient.register(accountRegister);
    }

    @Override
    public Account getAccount(Long userTelegramId) {
        List<AccountEntity> accountEntities = accountWebClient.getAccounts(userTelegramId);
        if (accountEntities.isEmpty()) {
            throw new NotFoundException("Accounts not found");
        } else if (accountEntities.size() > 1) {
            throw new ABSServerException("Multiple accounts found");
        }
        return modelMapper.map(accountEntities.get(0), Account.class);
    }
}

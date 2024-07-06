package ru.gpbf.middle;

import ru.gpbf.middle.domain.Account;
import ru.gpbf.middle.domain.factory.AccountFactory;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.dto.CreateAccountRequest;
import ru.gpbf.middle.entity.AccountEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AccountData {
    private final static String ACCOUNT_TYPE = "promotion";
    public final static AccountRegister ACCOUNT_REGISTER = AccountFactory.getAccount(UserData.USER_ID, ACCOUNT_TYPE);
    public final static CreateAccountRequest CREATE_ACCOUNT_REQUEST_PROMOTION = new CreateAccountRequest(UserData.USER_ID, ACCOUNT_TYPE);
    public final static CreateAccountRequest CREATE_ACCOUNT_REQUEST_NOT_PROMOTION = new CreateAccountRequest(UserData.USER_ID, "test");

    public final static List<AccountEntity> ACCOUNT_ENTITY_LIST = List.of(new AccountEntity(UUID.fromString("b3cbed21-5834-4f9f-a4a0-72c3abf2a260"), "Акционный", new BigDecimal("5000.00")));
    public final static Account ACCOUNT = new Account(UUID.fromString("b3cbed21-5834-4f9f-a4a0-72c3abf2a260"), "Акционный", new BigDecimal("5000.00"));
}

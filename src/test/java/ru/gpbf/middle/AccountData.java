package ru.gpbf.middle;

import ru.gpbf.middle.domain.factory.AccountFactory;
import ru.gpbf.middle.domain.factory.AccountRegister;
import ru.gpbf.middle.dto.CreateAccountRequest;

public class AccountData {
    private final static String ACCOUNT_TYPE = "promotion";
    public final static AccountRegister ACCOUNT_REGISTER = AccountFactory.getAccount(UserData.userId, ACCOUNT_TYPE);
    public final static CreateAccountRequest CREATE_ACCOUNT_REQUEST_PROMOTION = new CreateAccountRequest(UserData.userId, ACCOUNT_TYPE);
    public final static CreateAccountRequest CREATE_ACCOUNT_REQUEST_NOT_PROMOTION = new CreateAccountRequest(UserData.userId, "test");
}

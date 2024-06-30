package ru.gpbf.middle.domain.factory;

import ru.gpbf.middle.exception.BadRequest;

public class AccountFactory {

    public static AccountRegister getAccount(Long userTelegramId, String type) {
        switch (type) {
            case "promotion" -> {
                return new AccountRegister(userTelegramId,"Акционный");
            }
            default -> {
                throw new BadRequest("account name " + type + " not supported");
            }
        }
    }
}

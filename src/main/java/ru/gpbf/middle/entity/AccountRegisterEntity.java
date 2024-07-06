package ru.gpbf.middle.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class AccountRegisterEntity {
    private String accountName;

    private AccountRegisterEntity() {
    }

    @JsonCreator
    public AccountRegisterEntity(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountName='" + accountName + '\'' +
                '}';
    }
}

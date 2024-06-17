package ru.gpbf.middle.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class AccountEntity {
    private String accountName;

    private AccountEntity() {
    }

    @JsonCreator
    public AccountEntity(String accountName) {
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

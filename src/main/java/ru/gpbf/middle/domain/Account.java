package ru.gpbf.middle.domain;

import java.math.BigDecimal;
import java.util.UUID;

public final class Account {
    private UUID accountId;
    private String accountName;
    private BigDecimal amount;

    private Account() {
    }

    public Account(UUID accountId, String accountName, BigDecimal amount) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.amount = amount;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", amount=" + amount +
                '}';
    }
}

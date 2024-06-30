package ru.gpbf.middle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class CreateAccountRequest {
    @NotNull
    private Long userTelegramId;
    @NotBlank
    private String accountName;

    private CreateAccountRequest() {
    }

    @JsonCreator
    public CreateAccountRequest(Long userTelegramId, String accountName) {
        this.userTelegramId = userTelegramId;
        this.accountName = accountName;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "userTelegramId=" + userTelegramId +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}

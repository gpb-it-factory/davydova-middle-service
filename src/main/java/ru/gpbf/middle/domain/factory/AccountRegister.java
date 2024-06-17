package ru.gpbf.middle.domain.factory;

public final class AccountRegister {
    private Long userTelegramId;
    private String name;

    private AccountRegister() {
    }

    AccountRegister(Long userTelegramId, String name) {
        this.userTelegramId = userTelegramId;
        this.name = name;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AccountRegister{" +
                "userTelegramId=" + userTelegramId +
                ", name='" + name + '\'' +
                '}';
    }
}

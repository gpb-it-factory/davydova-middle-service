package ru.gpbf.middle.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class User {
    private Long userTelegramId;
    private String userName;

    private User() {
    }

    @JsonCreator
    public User(Long userTelegramId, String userName) {
        this.userTelegramId = userTelegramId;
        this.userName = userName;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getUserName() {
        return userName;
    }


    @Override
    public String toString() {
        return "User{" +
                "userTelegramId=" + userTelegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

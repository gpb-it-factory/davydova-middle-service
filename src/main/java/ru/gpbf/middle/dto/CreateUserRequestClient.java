package ru.gpbf.middle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.gpbf.middle.exception.BadRequest;

public class CreateUserRequestClient {
    private final Long userTelegramId;
    private final String userName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateUserRequestClient(Long userTelegramId, String userName) {
        if (userTelegramId == null) {
            throw new BadRequest("userTelegramId can not be null");
        }
        if (userName == null) {
            throw new BadRequest("userName can not be null");
        }
        this.userTelegramId = userTelegramId;
        this.userName = userName;
    }

    public long getUserTelegramId() {
        return userTelegramId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "CreateUserRequestClient{" +
                "userTelegramId=" + userTelegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

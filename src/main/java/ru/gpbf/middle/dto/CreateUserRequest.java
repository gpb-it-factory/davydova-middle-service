package ru.gpbf.middle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.gpbf.middle.exception.BadRequest;

public class CreateUserRequest {
    private final Long userTelegramId;
    private final String userName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateUserRequest(Long userTelegramId, String userName) {
        if (userTelegramId == null) {
            throw new BadRequest("userTelegramId can not be null");
        }
        if (userName == null || userName.isEmpty()) {
            throw new BadRequest("userName can not be null or empty");
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
        return "CreateUserRequest{" +
                "userTelegramId=" + userTelegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

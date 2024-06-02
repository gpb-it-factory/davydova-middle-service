package ru.gpbf.middle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.gpbf.middle.exception.BadRequest;

public class CreateUserRequestClient {

    private final Long userTelegramId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateUserRequestClient(Long userTelegramId) {
        if (userTelegramId == null) {
            throw new BadRequest("userTelegramId can not be null");
        }
        this.userTelegramId = userTelegramId;
    }

    public long getUserTelegramId() {
        return userTelegramId;
    }


    @Override
    public String toString() {
        return "UserRequest{" +
                "userTelegramId=" + userTelegramId +
                '}';
    }
}

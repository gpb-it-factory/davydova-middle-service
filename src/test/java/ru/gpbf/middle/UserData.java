package ru.gpbf.middle;

import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.dto.CreateUserRequest;

public class UserData {
    public static final Long userId = 1L;
    public static final String userName = "alina";
    public static final User user = new User(userId, userName);
    public static final CreateUserRequest createUserRequestClient = new CreateUserRequest(userId, userName);
}

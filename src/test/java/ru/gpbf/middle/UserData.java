package ru.gpbf.middle;

import ru.gpbf.middle.domain.User;
import ru.gpbf.middle.dto.CreateUserRequest;

public class UserData {
    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "alina";
    public static final User USER = new User(USER_ID, USER_NAME);
    public static final CreateUserRequest CREATE_USER_REQUEST_CLIENT = new CreateUserRequest(USER_ID, USER_NAME);

}

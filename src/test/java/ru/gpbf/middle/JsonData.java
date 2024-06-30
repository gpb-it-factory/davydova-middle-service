package ru.gpbf.middle;

public class JsonData {
    public final static String CREATE_USER_REQUEST = "{\"userTelegramId\":1,\"userName\":\"alina\"}";
    public final static String CREATE_ACCOUNT_REQUEST = "{\"userTelegramId\": 1, \"accountName\": \"promotion\"}";
    public final static String CREATE_ACCOUNT_REQUEST_BAD_ACCOUNT_NAME = "{\"userTelegramId\": 1, \"accountName\": \"test\"}";

    public static String getCreateUserConflictBackResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpbf/middle/json/UserConflictBack.json");
    }

    public static String getCreateUserConflictResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpbf/middle/json/UserConflict.json");
    }

    public static String getCreateAccountConflictBackResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpbf/middle/json/AccountConflictBack.json");
    }

    public static String getCreateAccountConflictResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpbf/middle/json/AccountConflict.json");
    }

    public static String getUnknownBackServerErrorResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpbf/middle/json/UnknownBackErrorBack.json");
    }

    public static String getUnknownErrorResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpbf/middle/json/UnknownBackError.json");
    }
}

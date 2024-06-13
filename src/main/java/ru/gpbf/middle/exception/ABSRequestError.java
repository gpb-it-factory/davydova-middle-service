package ru.gpbf.middle.exception;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;


public final class ABSRequestError {
    private String message;
    private String type;
    private String code;
    private UUID traceId;

    //need for jackson https://stackoverflow.com/questions/30568353/how-to-de-serialize-an-immutable-object-without-default-constructor-using-object#answer-46601536
    private ABSRequestError() {
    }

    @JsonCreator
    public ABSRequestError(String message, String type, String code, UUID traceId) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.traceId = traceId;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public UUID getTraceId() {
        return traceId;
    }

    @Override
    public String toString() {
        return "ABSRequestError{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", traceId=" + traceId +
                '}';
    }
}

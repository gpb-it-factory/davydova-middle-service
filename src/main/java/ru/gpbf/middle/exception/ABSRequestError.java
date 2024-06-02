package ru.gpbf.middle.exception;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;


public class ABSRequestError {
    private String message;
    private String type;
    private String code;
    private UUID trace_id;

    //need for jackson https://stackoverflow.com/questions/30568353/how-to-de-serialize-an-immutable-object-without-default-constructor-using-object#answer-46601536
    private ABSRequestError() {
    }

    @JsonCreator
    public ABSRequestError(String message, String type, String code, UUID trace_id) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.trace_id = trace_id;
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

    public UUID getTrace_id() {
        return trace_id;
    }

    @Override
    public String toString() {
        return "ABSRequestError{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", trace_id=" + trace_id +
                '}';
    }
}

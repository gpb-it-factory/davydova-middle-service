package ru.gpbf.middle.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public final class ErrorEntity {
    private String message;
    private String type;
    private String code;
    private UUID traceId;

    //need for jackson and effectively  final class field
    private ErrorEntity() {
    }

    @JsonCreator
    public ErrorEntity(String message, String type, String code, UUID traceId) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", traceId=" + traceId +
                '}';
    }
}

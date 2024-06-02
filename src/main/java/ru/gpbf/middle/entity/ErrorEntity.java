package ru.gpbf.middle.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public class ErrorEntity {
    private String message;
    private String type;
    private String code;
    private UUID trace_id;

    //need for jackson and effectively  final class field
    public ErrorEntity() {
    }

    @JsonCreator
    public ErrorEntity(String message, String type, String code, UUID trace_id) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.trace_id = trace_id;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", trace_id=" + trace_id +
                '}';
    }
}

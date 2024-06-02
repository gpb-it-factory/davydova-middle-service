package ru.gpbf.middle.dto;

import java.util.UUID;

public class ErrorResponseTo {
    private String message;
    private String type;
    private String code;
    private UUID trace_id;

    public ErrorResponseTo() {
    }

    public ErrorResponseTo(String message, String type, String code, UUID trace_id) {
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
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", trace_id=" + trace_id +
                '}';
    }
}

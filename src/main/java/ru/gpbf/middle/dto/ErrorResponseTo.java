package ru.gpbf.middle.dto;

import java.util.UUID;

public final class ErrorResponseTo {
    private String message;
    private String type;
    private String code;
    private UUID traceId;

    private ErrorResponseTo() {
    }

    public ErrorResponseTo(String message, String type, String code, UUID traceId) {
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
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", traceId=" + traceId +
                '}';
    }
}

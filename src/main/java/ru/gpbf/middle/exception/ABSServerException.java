package ru.gpbf.middle.exception;

import java.util.UUID;

public class ABSServerException extends RuntimeException {
    private final UUID traceId;
    private final String code;

    public ABSServerException(String message) {
        super(message);
        this.traceId = UUID.randomUUID();
        this.code = "500";
    }

    public ABSServerException(String message, UUID traceId, String code) {
        super(message);
        this.traceId = traceId;
        this.code = code;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public String getCode() {
        return code;
    }
}

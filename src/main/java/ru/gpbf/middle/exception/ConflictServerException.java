package ru.gpbf.middle.exception;

import java.util.UUID;

public final class ConflictServerException extends RuntimeException {
    private final UUID traceId;
    public ConflictServerException(String message, UUID traceId) {
        super(message);
        this.traceId = traceId;
    }

    public UUID getTraceId() {
        return traceId;
    }
}

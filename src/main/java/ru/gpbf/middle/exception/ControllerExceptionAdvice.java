package ru.gpbf.middle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gpbf.middle.dto.ErrorResponseTo;

import java.util.UUID;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(ABSServerException.class)
    public ResponseEntity<ErrorResponseTo> handleBackServerException(ABSServerException e) {
        ErrorResponseTo response = new ErrorResponseTo(e.getMessage(), "Back server error", e.getCode(), e.getTraceId());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadRequest.class})
    public ResponseEntity<ErrorResponseTo> handleBadRequestException(Exception e) {
        ErrorResponseTo response = new ErrorResponseTo(e.getMessage(), "Bad request", "400", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConflictServerException.class})
    public ResponseEntity<ErrorResponseTo> handleConflictException(ConflictServerException e) {
        ErrorResponseTo response = new ErrorResponseTo(e.getMessage(), "Bad request", "409", e.getTraceId());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResponseTo> handleNotFound(NotFoundException e) {
        ErrorResponseTo response = new ErrorResponseTo(e.getMessage(), e.getMessage(), "404", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponseTo> handleJacksonException(Exception e) {
        ErrorResponseTo response = new ErrorResponseTo(e.getMessage(), "Bad request", "400", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseTo> handleUnexpectedException(Exception e) {
        ErrorResponseTo response = new ErrorResponseTo(e.getMessage(), "Middle service exception", "500", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

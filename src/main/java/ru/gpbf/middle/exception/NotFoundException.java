package ru.gpbf.middle.exception;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
        System.out.println("kk");
    }
}

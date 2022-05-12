package ru.spbstu.afishakino.exception;

public class NotFoundSessionException extends RuntimeException {
    public NotFoundSessionException(String message) {
        super(message);
    }
}

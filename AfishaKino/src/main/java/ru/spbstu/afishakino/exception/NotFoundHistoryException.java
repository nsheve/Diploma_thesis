package ru.spbstu.afishakino.exception;

public class NotFoundHistoryException extends RuntimeException {
    public NotFoundHistoryException(String message) {
        super(message);
    }
}

package ru.spbstu.afishakino.exception;

public class NotFoundScheduleException extends RuntimeException {
    public NotFoundScheduleException(String message) {
        super(message);
    }
}

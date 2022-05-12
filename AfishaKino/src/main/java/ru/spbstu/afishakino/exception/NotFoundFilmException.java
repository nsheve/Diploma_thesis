package ru.spbstu.afishakino.exception;

public class NotFoundFilmException extends RuntimeException {
    public NotFoundFilmException(String message) {
        super(message);
    }
}

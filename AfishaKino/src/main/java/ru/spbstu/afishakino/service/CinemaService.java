package ru.spbstu.afishakino.service;

import ru.spbstu.afishakino.entity.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAllListCinema();
    Cinema findCinemaId(long id);
    Cinema addCinema(Cinema cinema);
    Cinema updateCinema(Cinema cinema, long id);
    void deleteCinema(long id);
    Cinema findCinemaName(String name);
}

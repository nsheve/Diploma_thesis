package ru.spbstu.afishakino.service;

import ru.spbstu.afishakino.entity.Film;

import java.util.List;

public interface FilmService {

    List<Film> getAllListFilm();
    Film addFilm(Film film);
    Film updateFilm(Film film, long id);
    Film findFilmId(long id);
    void deleteFilm(long id);
    Film findFilmTitle(String title);
}

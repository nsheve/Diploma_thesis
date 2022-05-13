package ru.spbstu.afishakino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.afishakino.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbstu.afishakino.service.FilmService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/afisha")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() {
        return new ResponseEntity<>(filmService.getAllListFilm(), HttpStatus.OK);
    }

    @GetMapping("/filmById/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity(filmService.findFilmId(id), HttpStatus.OK);
    }

    @GetMapping("/filmByTitle/{title}")
    public ResponseEntity findByTitle(@PathVariable String title) {
        return new ResponseEntity(filmService.findFilmTitle(title), HttpStatus.OK);
    }

    @GetMapping("/filmFilterGenre/{genre}")
    public ResponseEntity<List<Film>> findByGenre(@PathVariable String genre) {
        List<Film> filterFilmByGenre = filmService.getAllListFilm()
                .stream()
                .filter(el -> el.getGenre().contains(genre))
                .collect(Collectors.toList());
        if (filterFilmByGenre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this genre");
        }
        return new ResponseEntity<>(filterFilmByGenre, HttpStatus.OK);
    }

    
}

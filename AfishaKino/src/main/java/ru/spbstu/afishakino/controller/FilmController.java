package ru.spbstu.afishakino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.afishakino.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.spbstu.afishakino.service.FilmService;

import java.util.Comparator;
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

    @GetMapping("/filmFilterCountry/{country}")
    public ResponseEntity<List<Film>> findByCountry(@PathVariable String country) {
        List<Film> filterFilmByCountry = filmService.getAllListFilm()
                .stream()
                .filter(el -> el.getCountry().contains(country))
                .collect(Collectors.toList());
        if (filterFilmByCountry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this country");
        }
        return new ResponseEntity<>(filterFilmByCountry, HttpStatus.OK);
    }

    @GetMapping("/filmFilterRate/{rate}")
    public ResponseEntity<List<Film>> findByRate(@PathVariable double rate) {
        List<Film> filterFilmByRate = filmService.getAllListFilm()
                .stream()
                .filter(el -> el.getRate() > rate)
                .collect(Collectors.toList());
        if (filterFilmByRate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this rate");
        }
        return new ResponseEntity<>(filterFilmByRate, HttpStatus.OK);
    }

    @GetMapping("/filmSortedRate")
    public ResponseEntity<List<Film>> sortedByRate() {
        List<Film> sortedFilmByRate = filmService.getAllListFilm()
                .stream()
                .sorted(Comparator.comparingDouble(Film::getRate).reversed())
                .collect(Collectors.toList());
        if (sortedFilmByRate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this rate");
        }
        return new ResponseEntity<>(sortedFilmByRate, HttpStatus.OK);
    }

    @GetMapping("/filmSortedContentRate")
    public ResponseEntity<List<Film>> sortedByContentRate() {
        List<Film> sortedFilmByContentRate = filmService.getAllListFilm()
                .stream()
                .sorted(Comparator.comparingInt(Film::getContentRate))
                .collect(Collectors.toList());
        if (sortedFilmByContentRate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this content rate");
        }
        return new ResponseEntity<>(sortedFilmByContentRate, HttpStatus.OK);
    }

    @GetMapping("/filmFilterByProducer/{producer}")
    public ResponseEntity<List<Film>> filterByProducer(@PathVariable String producer) {
        List<Film> filterFilmByProducer = filmService.getAllListFilm()
                .stream()
                .filter(el -> el.getProducer().contains(producer))
                .collect(Collectors.toList());
        if (filterFilmByProducer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this producer");
        }
        return new ResponseEntity<>(filterFilmByProducer, HttpStatus.OK);
    }

    @GetMapping("/filmFilterByActor/{actor}")
    public ResponseEntity<List<Film>> filterByActor(@PathVariable String actor) {
        List<Film> filterFilmByActor = filmService.getAllListFilm()
                .stream()
                .filter(el -> el.getActors().contains(actor))
                .collect(Collectors.toList());
        if (filterFilmByActor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this actor");
        }
        return new ResponseEntity<>(filterFilmByActor, HttpStatus.OK);
    }

    @GetMapping("/filmFilterByDateSession/{dateSession}")
    public ResponseEntity<List<Film>> filterByDateSession(@PathVariable String dateSession) {
        List<Film> filterFilmByDateSession = filmService.getAllListFilm()
                .stream()
                .filter(el -> el.getDateSessionTime().contains(dateSession))
                .collect(Collectors.toList());
        if (filterFilmByDateSession.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no movie with this dateSession");
        }
        return new ResponseEntity<>(filterFilmByDateSession, HttpStatus.OK);
    }

    @GetMapping("/getImgFilmId/{id}")
    public ResponseEntity getImgFilm(@PathVariable long id) {
        return new ResponseEntity(filmService.findFilmId(id).getImage(), HttpStatus.OK);
    }

    @GetMapping("/getImgFilmTitle/{title}")
    public ResponseEntity getImgFilm(@PathVariable String title) {
        return new ResponseEntity(filmService.findFilmTitle(title).getImage(), HttpStatus.OK);
    }
}

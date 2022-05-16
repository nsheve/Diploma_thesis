package ru.spbstu.afishakino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "FilmController", description = "Получаем инфу по фильму + разные фильтры")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    @Operation(summary = "Информация о всех фильмах", description = "Получаем все информацию о фильме(Жанр, название и тд, кроме сеанса и названия кинотеатре)")
    public ResponseEntity<List<Film>> getAllFilms() {
        return new ResponseEntity<>(filmService.getAllListFilm(), HttpStatus.OK);
    }

    @GetMapping("/filmById/{id}")
    @Operation(summary = "Информация о фильме по id", description = "По определенному id получаем информацию о фильме")
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity(filmService.findFilmId(id), HttpStatus.OK);
    }

    @GetMapping("/filmByTitle/{title}")
    @Operation(summary = "Информация о фильме по названию", description = "По определенному названию фильма получаем все информацию о фильме")
    public ResponseEntity findByTitle(@PathVariable String title) {
        return new ResponseEntity(filmService.findFilmTitle(title), HttpStatus.OK);
    }

    @GetMapping("/filmFilterGenre/{genre}")
    @Operation(summary = "Фильтр по жанру", description = "Получаем фильмы по определенному жанру")
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
    @Operation(summary = "Фильтр по стране", description = "Получаем фильмы по опредленной стране")
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
    @Operation(summary = "Фильтр по рейтингу фильма", description = "Получаем фильмы если их рейтинг больше определенного ")
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
    @Operation(summary = "Сортировка по рейтингу", description = "Сортируем фильмы по их рейтингу в начале будут с самым высоким")
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
    @Operation(summary = "Сортировка по возрасту", description = "Сортируем фильмы по возрасту в начале от 0+ и до 18+")
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
    @Operation(summary = "Фильтр по режиссеру", description = "Получаем фильмы, который снял определенный режиссер")
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
    @Operation(summary = "Фильтр по актеру", description = "Получаем фильмы, в которых снялся определенный актер")
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
    @Operation(summary = "", description = "")
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
    @Operation(summary = "Постер по id фильма", description = "Получаем картинку(постер) по id фильма")
    public ResponseEntity getImgFilm(@PathVariable long id) {
        return new ResponseEntity(filmService.findFilmId(id).getImage(), HttpStatus.OK);
    }

    @GetMapping("/getImgFilmTitle/{title}")
    @Operation(summary = "Постер по названию фильма", description = "Получаем картинку(постер) по названию фильма")
    public ResponseEntity getImgFilm(@PathVariable String title) {
        return new ResponseEntity(filmService.findFilmTitle(title).getImage(), HttpStatus.OK);
    }
}

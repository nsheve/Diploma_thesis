package ru.spbstu.afishakino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbstu.afishakino.entity.Cinema;
import ru.spbstu.afishakino.service.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/afisha")
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/cinemas")
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return new ResponseEntity<>(cinemaService.getAllListCinema(), HttpStatus.OK);
    }

    @GetMapping("/cinemaById/{id}")
    public ResponseEntity findCinemaById(@PathVariable long id) {
        return new ResponseEntity(cinemaService.findCinemaId(id), HttpStatus.OK);
    }

    @GetMapping("/cinemaByName/{name}")
    public ResponseEntity findCinemaByName(@PathVariable String name) {
        return new ResponseEntity(cinemaService.findCinemaName(name), HttpStatus.OK);
    }
}

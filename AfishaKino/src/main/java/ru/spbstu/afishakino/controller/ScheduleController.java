package ru.spbstu.afishakino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.afishakino.entity.Schedule;
import ru.spbstu.afishakino.entity.Session;
import ru.spbstu.afishakino.service.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/afisha")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return new ResponseEntity<>(scheduleService.getAllScheduleList(), HttpStatus.OK);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity(scheduleService.findSchedule(id), HttpStatus.OK);
    }

    @GetMapping("/films/{day}")//Все фильмы(с их сеансами) в определенный день
    public ResponseEntity<List<Schedule>> getFilmsAtCurrentDay(@PathVariable("day") String day) {
        List<Schedule> filteredFilmsAtCurrentDay = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getSession().getDateTime().equals(day)).collect(Collectors.toList());
        if (filteredFilmsAtCurrentDay.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Films not found");
        }
        return new ResponseEntity<>(filteredFilmsAtCurrentDay, HttpStatus.OK);
    }

    @GetMapping("/filmSessionsById/{id}")//Все сеансы определенного фильма по ID
    public ResponseEntity<List<Session>> getFilmWeekSessionsById(@PathVariable("id") long id) {
        List<Session> filmWeekSessions = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getFilm().getId() == id).map(Schedule::getSession).collect(Collectors.toList());
        if (filmWeekSessions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }
        return new ResponseEntity<>(filmWeekSessions, HttpStatus.OK);
    }

    @GetMapping("/filmSessionsByTitle/{title}")//Все сеансы определенного фильма по title
    public ResponseEntity<List<Session>> getFilmWeekScheduleByTitle(@PathVariable("title") String title) {
        List<Session> filmWeekSessions = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getFilm().getTitle().equals(title)).map(Schedule::getSession).collect(Collectors.toList());
        if (filmWeekSessions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }
        return new ResponseEntity<>(filmWeekSessions, HttpStatus.OK);
    }

    @GetMapping("/cinemaScheduleById/{id}")//Расписание в данном кинотеатре (по его Id)
    public ResponseEntity<List<Schedule>> getCinemaSchedule(@PathVariable("id") long id) {
        List<Schedule> filmsInCinema = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getCinema().getId() == id).collect(Collectors.toList());
        if (filmsInCinema.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessions & Films in this cinema not found");
        }
        return new ResponseEntity<>(filmsInCinema, HttpStatus.OK);
    }

    @GetMapping("/cinemaScheduleByName/{name}")//Расписание в данном кинотеатре (по его названию)
    public ResponseEntity<List<Schedule>> getCinemaSchedule(@PathVariable("name") String name) {
        List<Schedule> filmsInCinema = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getCinema().getName().equals(name)).collect(Collectors.toList());
        if (filmsInCinema.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessions & Films in this cinema not found");
        }
        return new ResponseEntity<>(filmsInCinema, HttpStatus.OK);
    }
}

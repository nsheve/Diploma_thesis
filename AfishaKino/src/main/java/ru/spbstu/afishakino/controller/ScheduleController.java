package ru.spbstu.afishakino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "ScheduleController", description = "по сути в этом контроллере можно смотреть данные о фильме + время сеанса + в каком кинотеатре будет фильм")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedules")
    @Operation(summary = "Получаем всею инфу о расписании фильма", description = "Кратко говоря, получаем все информацию о каждом фильме + его сеансу + в каком кинотетаре")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return new ResponseEntity<>(scheduleService.getAllScheduleList(), HttpStatus.OK);
    }

    @GetMapping("/schedule/{id}")
    @Operation(summary = "Получаем по id расписание фильма", description = "Получаем по id, расписание фильма + кинотеатр + информацию о фильме")
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity(scheduleService.findSchedule(id), HttpStatus.OK);
    }

    @GetMapping("/films/{day}")
    @Operation(summary = "Фильмы с их сеансами", description = "Все фильмы(с их сеансами) в определенный день, день у меня хранится в String(если брать наш день, то дата = сегодня иначе число+месяц)")
    public ResponseEntity<List<Schedule>> getFilmsAtCurrentDay(@PathVariable("day") String day) {
        List<Schedule> filteredFilmsAtCurrentDay = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getSession().getDateTime().equals(day)).collect(Collectors.toList());
        if (filteredFilmsAtCurrentDay.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Films not found");
        }
        return new ResponseEntity<>(filteredFilmsAtCurrentDay, HttpStatus.OK);
    }

    @GetMapping("/filmSessionsById/{id}")
    @Operation(summary = "Получаем сеансы фильма", description = "Все сеансы определенного фильма по ID")
    public ResponseEntity<List<Schedule>> getFilmWeekSessionsById(@PathVariable("id") long id) {
        List<Schedule> filmWeekSessions = scheduleService.getAllScheduleList().stream()
                .filter(schedule -> schedule.getFilm().getId() == id)
                .collect(Collectors.toList());
        if (filmWeekSessions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }
        return new ResponseEntity<>(filmWeekSessions, HttpStatus.OK);
    }

    @GetMapping("/filmSessionsByTitle/{title}")
    @Operation(summary = "Получить сеансы по названию фильма", description = "Все сеансы определенного фильма по title")
    public ResponseEntity<List<Schedule>> getFilmWeekScheduleByTitle(@PathVariable("title") String title) {
        List<Schedule> filmWeekSessions = scheduleService.getAllScheduleList().stream()
                .filter(schedule -> schedule.getFilm().getTitle().equals(title))
                .collect(Collectors.toList());
        if (filmWeekSessions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }
        return new ResponseEntity<>(filmWeekSessions, HttpStatus.OK);
    }

    @GetMapping("/cinemaScheduleById/{id}")
    @Operation(summary = "Получаем расписание в кинотеатре(по id)", description = "Расписание в данном кинотеатре (по его Id)")
    public ResponseEntity<List<Schedule>> getCinemaSchedule(@PathVariable("id") long id) {
        List<Schedule> filmsInCinema = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getCinema().getId() == id).collect(Collectors.toList());
        if (filmsInCinema.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessions & Films in this cinema not found");
        }
        return new ResponseEntity<>(filmsInCinema, HttpStatus.OK);
    }

    @GetMapping("/cinemaScheduleByName/{name}")
    @Operation(summary = "Получаем расписание в кинотеатре(по Названию)", description = "Расписание в данном кинотеатре (по его названию)")
    public ResponseEntity<List<Schedule>> getCinemaSchedule(@PathVariable("name") String name) {
        List<Schedule> filmsInCinema = scheduleService.getAllScheduleList().stream().filter(schedule -> schedule.getCinema().getName().equals(name)).collect(Collectors.toList());
        if (filmsInCinema.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessions & Films in this cinema not found");
        }
        return new ResponseEntity<>(filmsInCinema, HttpStatus.OK);
    }
}

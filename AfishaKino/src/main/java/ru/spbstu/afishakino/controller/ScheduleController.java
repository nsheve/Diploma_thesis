package ru.spbstu.afishakino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbstu.afishakino.entity.Schedule;
import ru.spbstu.afishakino.service.ScheduleService;

import java.util.List;

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
}

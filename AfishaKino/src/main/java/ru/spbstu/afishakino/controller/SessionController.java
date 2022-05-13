package ru.spbstu.afishakino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbstu.afishakino.entity.Session;
import ru.spbstu.afishakino.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/afisha")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSessions() {
        return new ResponseEntity<>(sessionService.listSession(), HttpStatus.OK);
    }
}

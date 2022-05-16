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
import ru.spbstu.afishakino.entity.Session;
import ru.spbstu.afishakino.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/afisha")
@Tag(name = "SessionController", description = "Получаем время сеансов")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    @Operation(summary = "Все сеансы", description = "Вывод времени всех сеансов")
    public ResponseEntity<List<Session>> getAllSessions() {
        return new ResponseEntity<>(sessionService.listSession(), HttpStatus.OK);
    }

    @GetMapping("/sessionById/{id}")
    @Operation(summary = "Сеанс по id", description = "Показывает время сеанса по id")
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity(sessionService.findSession(id), HttpStatus.OK);
    }

    @GetMapping("/sessionByTime/{time}")
    @Operation(summary = "Сеанс по времени", description = "Показывает время сеанса если ввести время(время хранится в string)")
    public ResponseEntity findById(@PathVariable String time) {
        return new ResponseEntity(sessionService.findSessionTime(time), HttpStatus.OK);
    }
}

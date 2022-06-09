package ru.spbstu.afishakino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.afishakino.entity.History;
import ru.spbstu.afishakino.exception.NotFoundHistoryException;
import ru.spbstu.afishakino.service.HistoryService;
import ru.spbstu.afishakino.service.implement.JavaMailSenderImpl;

import java.util.List;

@RestController
@RequestMapping("/user/history")
@Tag(name = "HistoryController", description = "Нужен для показа заказов пользователя, добавления и удаления")
public class HistoryController {
    private final JavaMailSenderImpl javaMailSender;
    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService, JavaMailSenderImpl javaMailSender) {
        this.historyService = historyService;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/histories")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Вывод всех историй, только для зарегистрированных пользователей",
            description = "Позволяет показать все фильмы пользователя, которые добавил в историю")
    public ResponseEntity<List<History>> listHistory() {
        return new ResponseEntity<>(historyService.listHistory(), HttpStatus.OK);
    }

    @PostMapping(value = "/createHistory", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Добавления фильма, только для зарегистрированных пользователей", description = "Позволяет добавить фильм в историю для просмотра")
    public History createHistory(@RequestBody History newHistory) {
        //javaMailSender.sendEmail(newHistory.getUser(), newHistory.getSchedule());
        return historyService.createHistory(newHistory);
    }

    @DeleteMapping("/deleteHistory/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Удаление истории, только для зарегистрированных пользователей", description = "Удаление пользователем из истории фильм, который хотел посмотреть")
    public void deleteHistory(@PathVariable("id") Long id) {
        try {
            historyService.deleteHistory(id);
        } catch (NotFoundHistoryException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "History not found");
        }
    }
}

package ru.spbstu.afishakino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.afishakino.entity.History;
import ru.spbstu.afishakino.exception.NotFoundHistoryException;
import ru.spbstu.afishakino.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/afisha")
public class HistoryController {
    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/histories")
    public ResponseEntity<List<History>> listHistory() {
        return new ResponseEntity<>(historyService.listHistory(), HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<History> findHistory(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(historyService.findHistory(id), HttpStatus.OK);
        } catch (NotFoundHistoryException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "History not found");
        }
    }

    @PostMapping(value = "/createHistory", consumes = "application/json", produces = "application/json")
    public History createHistory(@RequestBody History newHistory) {
        return historyService.createHistory(newHistory);
    }

    @DeleteMapping("/deleteHistory/{id}")
    public void deleteHistory(@PathVariable("id") Long id) {
        try {
            historyService.deleteHistory(id);
        } catch (NotFoundHistoryException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "History not found");
        }
    }
}

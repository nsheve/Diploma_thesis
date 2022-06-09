package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Session {
    private String cinemaName;
    private List<String> sessionTime;
}

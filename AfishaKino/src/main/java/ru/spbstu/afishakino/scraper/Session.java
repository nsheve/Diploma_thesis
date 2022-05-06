package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Session {
    private String cinemaName;
    private String sessionTime;
}

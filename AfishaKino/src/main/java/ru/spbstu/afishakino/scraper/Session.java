package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Session {
    private String cinemaName;
    private String sessionTime;
}

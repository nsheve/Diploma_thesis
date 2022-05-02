package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Film {
    private String name;
    private String contentRate;
    private String company;
    private String producer;
    private String genre;
    private String actors;
    private String rate;
    private String description;
}

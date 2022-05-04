package ru.spbstu.afishakino.scraper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Film {
    private String name;
    //private String contentRate;
    private String company;
    //private String producer;
    //private String genre;
    //private String actors;
    //private String rate;
    //private String description;
    private List<Session> sessionList;
}

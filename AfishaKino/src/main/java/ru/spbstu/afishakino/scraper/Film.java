package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Film {
    private String nameTitle;
    //private String country
    //private String company;
    //private String rate;
    //private String contentRate;
    //private String genre;
    //private String producer;
    //private String actors;
    //private String description;
    //private String image;
    private List<Session> sessionList;
}

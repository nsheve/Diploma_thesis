package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Film {
    private String nameTitle;
    private String country;
    private double rate;
    private int contentRate;
    private String genre;
    private String producer;
    private String actor;
    private String description;
    private String dateSessionFilm;
    private String image;
    private List<Session> sessionList;
}

package ru.spbstu.afishakino.scraper;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Film {
    private String nameTitle;
    private String country; //сделал
    private double rate; //сделал
    private int contentRate; //сделал
    private String genre; //сделал
    private String producer; //сделал
    private String actor;
    private String description;
    private String image; //сделал
    private List<Session> sessionList; // сделал
}

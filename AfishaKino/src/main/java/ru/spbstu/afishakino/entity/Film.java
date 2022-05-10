package ru.spbstu.afishakino.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String title;
    private String country;
    private double rate;
    private int contentRate;
    private String genre;
    private String producer;
    private String actors;
    private String description;
    private String dateSessionTime;
    private String image;

    public Film(String title, String country, double rate, int contentRate, String genre, String producer, String actors, String description, String dateSessionTime, String image) {
        this.title = title;
        this.country = country;
        this.rate = rate;
        this.contentRate = contentRate;
        this.genre = genre;
        this.producer = producer;
        this.actors = actors;
        this.description = description;
        this.dateSessionTime = dateSessionTime;
        this.image = image;
    }
}

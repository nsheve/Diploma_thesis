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
    @Column(length = 1000)
    private String country;
    @Column(length = 1000)
    private double rate;
    @Column(length = 1000)
    private int contentRate;
    @Column(length = 1000)
    private String genre;
    @Column(length = 1000)
    private String producer;
    @Column(length = 1000)
    private String actors;
    @Column(length = 6000)
    private String description;
    @Column(length = 1000)
    private String dateSessionTime;
    @Column(length = 1000)
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

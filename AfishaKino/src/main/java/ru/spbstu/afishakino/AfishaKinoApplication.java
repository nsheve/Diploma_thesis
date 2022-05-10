package ru.spbstu.afishakino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.spbstu.afishakino.entity.Schedule;
import ru.spbstu.afishakino.repository.*;
import ru.spbstu.afishakino.entity.Cinema;
import ru.spbstu.afishakino.entity.Film;
import ru.spbstu.afishakino.entity.Session;
import ru.spbstu.afishakino.scraper.WebScraper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@SpringBootApplication
public class AfishaKinoApplication {
    @Autowired
    private WebScraper webScraper;

    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    SessionRepository sessionRepository;

    private Film film;
    private Session session;
    private Cinema cinema;

    public static void main(String[] args) {
        SpringApplication.run(AfishaKinoApplication.class, args);
    }

    @Bean
    public void getScraping() throws IOException {
        webScraper.getFilmsList().forEach((scrapedFilm) -> {
            Optional<Film> filmOptional = filmRepository.findByTitle(scrapedFilm.getNameTitle());
            if (filmOptional.isPresent()) {
                film = filmOptional.get();
            } else {
                film = new Film(scrapedFilm.getNameTitle(),
                                scrapedFilm.getCountry(),
                                scrapedFilm.getRate(),
                                scrapedFilm.getContentRate(),
                                scrapedFilm.getGenre(),
                                scrapedFilm.getProducer(),
                                scrapedFilm.getActor(),
                                scrapedFilm.getDescription(),
                                scrapedFilm.getDateSessionFilm(),
                                scrapedFilm.getImage());
                filmRepository.save(film);
            }
        });
    }
}

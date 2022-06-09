package ru.spbstu.afishakino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.spbstu.afishakino.entity.*;
import ru.spbstu.afishakino.repository.*;
import ru.spbstu.afishakino.scraper.WebScraper;

import java.io.IOException;
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
    @Autowired
    RoleRepository roleRepository;

    private Film film;
    private Session session;
    private Cinema cinema;

    public static void main(String[] args) {
        SpringApplication.run(AfishaKinoApplication.class, args);
    }

    @Bean
    public void addRole() {
        roleRepository.save(new Role(ERole.ROLE_USER));
    }

    @Bean
    public void getScraping() throws IOException {
        //webScraper.getFilmsList().forEach(System.out::println); смотрим, правильность данных
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
            for (var cinemaScraper : scrapedFilm.getSessionList()) {
                Optional<Cinema> cinemaOptional = cinemaRepository.findByName(cinemaScraper.getCinemaName());
                if (cinemaOptional.isPresent()) {
                    cinema = cinemaOptional.get();
                } else {
                    cinema = new Cinema(cinemaScraper.getCinemaName());
                    cinemaRepository.save(cinema);
                }
                cinemaScraper.getSessionTime().forEach((time) -> {
                    Optional<Session> sessionOptional = sessionRepository.findByDateTime(time);
                    if (sessionOptional.isPresent()) {
                        sessionOptional.get();
                    } else {
                        session = new Session(time);
                        sessionRepository.save(session);
                    }
                    scheduleRepository.save(new Schedule(film, session, cinema));
                });
            }
        });
    }
}

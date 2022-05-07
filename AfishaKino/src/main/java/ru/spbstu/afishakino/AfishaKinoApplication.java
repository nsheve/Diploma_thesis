package ru.spbstu.afishakino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.spbstu.afishakino.scraper.WebScraper;

import java.io.IOException;

@SpringBootApplication
public class AfishaKinoApplication {
    @Autowired
    private WebScraper webScraper;

    public static void main(String[] args) {
        SpringApplication.run(AfishaKinoApplication.class, args);
    }

    @Bean
    public void getScraping() throws IOException {
        webScraper.getFilmsList().forEach(System.out::println);
    }
}

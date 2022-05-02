package ru.spbstu.afishakino.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    final private static String URL = "https://kinoteatr.ru/raspisanie-kinoteatrov/sankt-peterburg/raduga/?day=tomorrow";
    public static void main(String[] args) throws IOException {
        List<Film> films = new ArrayList<>();
        Document document = Jsoup.connect(URL).get();
        Elements elements = document.getElementsByAttribute("data-gtm-ec-id");
        for(Element row : elements) {
            final String detailsLink = row.attr("href");
            Film film = new Film();
            film.setName(row.select("span.movie_card_header.title").text());
            film.setCompany(row.select("span.sub_title.shedule_movie_text").text());
            Document doc = Jsoup.connect(detailsLink).get();
            film.setProducer(doc.getElementsByAttributeValue("itemprop", "director").text());
            film.setGenre(doc.getElementsByAttributeValue("itemprop", "genre").text());
            film.setActors(doc.getElementsByAttributeValue("itemprop", "actor").text());
            film.setDescription(doc.getElementsByAttributeValue("itemprop", "description").text());
            film.setRate(doc.getElementsByClass("rate").text());
            film.setContentRate(doc.getElementsByAttributeValue("itemprop", "contentRating").text());
            films.add(film);
        }
        
        films.forEach(System.out::println);
    }
}

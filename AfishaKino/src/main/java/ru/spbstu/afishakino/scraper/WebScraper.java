package ru.spbstu.afishakino.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebScraper {
    final private static String URL;
    final private static String CITY_URL;
    final private static List<Film> filmList;
    private static Document document;
    private static Elements elements;

    static {
        URL = "https://kinoteatr.ru";
        CITY_URL = "/kinoafisha/sankt-peterburg/";
        filmList = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        List<String> hrefFilmsList = getHrefFilms();
        for (String hrefFilm : hrefFilmsList) {
            filmList.add(getFilmInfo(hrefFilm));
        }
        filmList.forEach(System.out::println);
    }


    private static List<String> getHrefFilms() throws IOException {
        document = Jsoup.connect(URL + CITY_URL).get();
        List<String> hrefFilmList = new ArrayList<>();
        elements = document.getElementsByClass("movie_card_clickable");
        for(Element el : elements) {
            hrefFilmList.add(el.getElementsByTag("a").attr("href"));
        }
        return hrefFilmList;
    }

    private static List<Session> getNameCinemaAndSession() {
        List<Session> sessionList = new ArrayList<>();
        elements = document.getElementsByClass("cinema");
        for(Element element : elements) {
            String nameCinema = element.getElementsByTag("a").text();
            String sessionTime = element.getElementsByClass("time").text();
            sessionList.add(new Session(nameCinema, sessionTime));
        }
        return sessionList;
    }



    private static Film getFilmInfo(String hrefFilm) throws IOException {
        document = Jsoup.connect(hrefFilm).get();
        elements = document.getElementsByClass("info");
        String nameTitleFilm = "";
        List<Session> sessionList = new ArrayList<>();
        for(Element element : elements) {
            nameTitleFilm = element.getElementsByAttributeValue("itemprop", "name").first().text();
            sessionList = getNameCinemaAndSession();
            break;
        }
        return new Film(nameTitleFilm, sessionList);
    }
}

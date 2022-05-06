package ru.spbstu.afishakino.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WebScraper {
    final static private String URL;
    final static private String CITY_URL;
    final static private List<Film> filmList;
    static private Document document;

    static {
        URL = "https://kinoteatr.ru";
        CITY_URL = "/raspisanie-kinoteatrov/sankt-peterburg/";
        filmList = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        getNameCinemaAndSession(getHrefCinema());
        filmList.forEach(System.out::println);
        //el.forEach(System.out::println);
    }


    private static List<String> getHrefCinema() throws IOException {
        document = Jsoup.connect(URL + CITY_URL).get();
        List<String> hrefCinemaList = new ArrayList<>();
        Elements elements = document.getElementsByClass("col-md-12 cinema_card");
        for(Element el : elements) {
            hrefCinemaList.add(el.getElementsByTag("a").attr("href"));
        }
        return hrefCinemaList;
    }

    private static void getNameCinemaAndSession(List<String> hrefCinemaList) throws IOException {
        for(String href : hrefCinemaList) {
            document = Jsoup.connect(href).get();
            Elements elements = document.getElementsByClass("wrap cinema_card_single");
            for (Element el : elements) {
                getFilmInfo(el);
            }
        }
    }

    private static void getFilmInfo(Element el) {
        final String nameCinema = el.getElementsByAttributeValue("itemprop", "name").text();
        Elements elSession = document.getElementsByClass("shedule_movie_sessions col col-md-8");
        String[] arraySession = new String[elSession.size()];
        int index = 0;
        for(Element el1 : elSession) {
            arraySession[index] = el1.getElementsByClass("shedule_session_time").text();
            index++;
        }
        Elements elements = document.getElementsByClass("shedule_movie bordered gtm_movie");
        index = 0;
        for(Element element : elements) {
        List<Session> sessionList = new ArrayList<>();
            Film film = new Film();
            Session session = new Session();
            film.setName(element.getElementsByClass("movie_card_header title").text());
            //film.setCompany(element.getElementsByClass("sub_title shedule_movie_text").text());
            session.setCinemaName(nameCinema);
            session.setSessionTime(arraySession[index]);
            sessionList.add(session);
            film.setSessionList(sessionList);
            filmList.add(film);
            index++;
        }
    }
}

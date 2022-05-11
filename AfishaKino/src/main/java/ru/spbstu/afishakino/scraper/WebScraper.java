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
    private static Document document;
    private static Elements elements;
    private static int count;
    private static String nameTitleFilm;
    private static String country;
    private static Double rate;
    private static int contentRate;
    private static String genre;
    private static String producer;
    private static String actor;
    private static String dateSessionFilm;
    private static String description;

    static {
        URL = "https://kinoteatr.ru";
        CITY_URL = "/kinoafisha/sankt-peterburg/";
    }

    public static List<Film> getFilmsList() throws IOException {
        List<Film> filmList = new ArrayList<>();
        List<String> hrefFilmsList = getHrefFilms();
        List<Double> rateFilmsList = getRateFilms();
        List<String> imgFilmsList = getImgFilms();
        for (String hrefFilm : hrefFilmsList) {
          filmList.add(getFilmInfo(hrefFilm, imgFilmsList, rateFilmsList));
        }
        return filmList;
    }


    private static List<String> getHrefFilms() throws IOException {
        document = Jsoup.connect(URL + CITY_URL).get();
        List<String> hrefFilmList = new ArrayList<>();
        elements = document.getElementsByClass("col-md-2 col-sm-6 col-xs-12 movie_card");
        for(Element el : elements) {
            hrefFilmList.add(el.getElementsByTag("a").attr("href"));
        }
        return hrefFilmList;
    }

    private static List<Session> getNameCinemaAndSession() {
        List<Session> sessionList = new ArrayList<>();
        elements = document.getElementsByClass("cinema");
        for (Element element : elements) {
            String nameCinema = element.getElementsByTag("a").text();
            //тут не очень красиво, придумать как улучшить
            Elements els = element.getElementsByClass("time");
            List<String> sessionTimeList = new ArrayList<>();
            for (Element el : els) {
                sessionTimeList.add(el.getElementsByClass("time").text());
            }
            sessionList.add(new Session(nameCinema, sessionTimeList));
        }
        return sessionList;
    }

    private static Film getFilmInfo(String hrefFilm, List<String> imgFilmsList, List<Double> rateFilmsList) throws IOException {
        document = Jsoup.connect(hrefFilm).get();
        elements = document.getElementsByClass("info");
        List<Session> sessionList = new ArrayList<>();
        for (Element element : elements) {
            nameTitleFilm = element.getElementsByAttributeValue("itemprop", "name").first().text();
            String strContentRate = element.getElementsByClass("age").text();
            contentRate = Integer.parseInt(parseString(strContentRate));
            genre = element.getElementsByAttributeValue("itemprop", "genre").text();
            producer = isProducer(element.getElementsByAttributeValue("itemprop", "director").text());
            actor = isActors(element.getElementsByAttributeValue("itemprop", "actor").text());
            description = isDescription(element.getElementsByClass("announce").text());
            country = getCountryFilm();
            sessionList = getNameCinemaAndSession();
            dateSessionFilm = dateSession();
            break;
        }
        return new Film(nameTitleFilm,
                country,
                rateFilmsList.get(count),
                contentRate,
                genre,
                producer,
                actor,
                description,
                dateSessionFilm,
                imgFilmsList.get(count++),
                sessionList);
    }

    private static List<String> getImgFilms() {
        List<String> imgFilmsList = new ArrayList();
        elements = document.getElementsByClass("movie_card_image_wrap");
        for (Element element : elements) {
            imgFilmsList.add(element.getElementsByAttributeValue("itemprop", "image").attr("src"));
        }
        return imgFilmsList;
    }


    private static List<Double> getRateFilms() {
        List<Double> rateFilmsList = new ArrayList();
        for (Element element : elements) {
            String txtRate = element.getElementsByClass("movie_card_stars").text();
            rate = !txtRate.equals("") ? Double.parseDouble(txtRate) : 0.0;
            rateFilmsList.add(rate);
        }
        return rateFilmsList;
    }

    private static String getCountryFilm() {
        elements = document.getElementsByAttributeValue("itemprop", "countryOfOrigin");
        String country = "";
        for (Element element : elements) {
            country = element.getElementsByAttributeValue("itemprop", "name").first().text();
        }
        return !country.equals("") ? country : "Россия";
    }

    private static String parseString(String str) {
        int index = str.indexOf("+");
        return str.substring(0, index);
    }

    private static String isProducer(String producer) {
        return !producer.equals("") ? producer : "Нет продюсера";
    }

    private static String isActors(String actors) {
        return !actors.equals("") ? actors : "Нет актеров";
    }

    private static String isDescription(String descriptions) {
        return !descriptions.equals("") ? descriptions : "Нет информации";
    }

    private static String dateSession() {
        elements = document.getElementsByClass("date");
        String dateSessionFilm = "";
        for (Element element : elements) {
            dateSessionFilm = element.getElementsByClass("date").text();
        }
        return dateSessionFilm;
    }
}

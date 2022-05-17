package ru.spbstu.afishakino.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.spbstu.afishakino.entity.Schedule;
import ru.spbstu.afishakino.entity.User;

@Service
public class JavaMailSenderImpl {
    private final String mailSenderFrom = "nsheveafisha@gmail.com";
    private final JavaMailSender javaMailSender;

    @Autowired
    public JavaMailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user, Schedule schedule) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailSenderFrom);
        msg.setTo(user.getEmail());
        msg.setSubject("AfishaKino");
        msg.setText("Название фильма: " + schedule.getFilm().getTitle() + "\n" +
                    "Дата сеанса: " + schedule.getFilm().getDateSessionTime() + "\n" +
                    "Жанр: " + schedule.getFilm().getGenre() + "\n" +
                    "Возраст: " + schedule.getFilm().getContentRate() + "\n" +
                    "Кинотеатр: " + schedule.getCinema().getName() + "\n" +
                    "Сеанс: " + schedule.getSession().getDateTime());
        javaMailSender.send(msg);
    }
}

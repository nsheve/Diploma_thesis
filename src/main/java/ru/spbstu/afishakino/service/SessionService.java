package ru.spbstu.afishakino.service;

import ru.spbstu.afishakino.entity.Session;

import java.util.List;

public interface SessionService {

    List<Session> listSession();
    Session findSession(long id);
    Session findSessionTime(String time);
}

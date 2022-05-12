package ru.spbstu.afishakino.service;

import ru.spbstu.afishakino.entity.*;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllScheduleList();
    Schedule findSchedule(long id);
    Schedule createSchedule(Schedule schedule);
    Schedule updateSchedule(Schedule schedule, long id);
    void deleteSchedule(long id);
    List<Schedule> findSchedulesByFilmAndSession(Film film, Session session);
}

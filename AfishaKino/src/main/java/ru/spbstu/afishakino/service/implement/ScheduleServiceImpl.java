package ru.spbstu.afishakino.service.implement;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.afishakino.entity.Film;
import ru.spbstu.afishakino.entity.Schedule;
import ru.spbstu.afishakino.entity.Session;
import ru.spbstu.afishakino.exception.NotFoundScheduleException;
import ru.spbstu.afishakino.repository.ScheduleRepository;
import ru.spbstu.afishakino.service.ScheduleService;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> getAllScheduleList() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findSchedule(long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isPresent()) {
            return optionalSchedule.get();
        } else {
            throw new NotFoundScheduleException("Not found by id Schedule");
        }
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Schedule schedule, long id) {
        Schedule copySchedule = findSchedule(id);
        BeanUtils.copyProperties(schedule, copySchedule);
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(long id) {
        scheduleRepository.delete(findSchedule(id));
    }

    @Override
    public List<Schedule> findSchedulesByFilmAndSession(Film film, Session session) {
        return scheduleRepository.findAllByFilmAndSession(film, session);
    }
}

package ru.spbstu.afishakino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spbstu.afishakino.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

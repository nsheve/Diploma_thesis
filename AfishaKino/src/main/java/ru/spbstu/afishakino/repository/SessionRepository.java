package ru.spbstu.afishakino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spbstu.afishakino.entity.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByDateTime(String dateTime);
}

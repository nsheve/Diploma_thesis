package ru.spbstu.afishakino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spbstu.afishakino.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}

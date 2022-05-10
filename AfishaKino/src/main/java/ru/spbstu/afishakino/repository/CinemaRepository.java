package ru.spbstu.afishakino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spbstu.afishakino.entity.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}

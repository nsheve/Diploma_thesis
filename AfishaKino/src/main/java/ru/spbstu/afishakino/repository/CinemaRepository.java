package ru.spbstu.afishakino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spbstu.afishakino.entity.Cinema;

import java.util.Optional;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findByName(String cinemaName);
}

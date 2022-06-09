package ru.spbstu.afishakino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spbstu.afishakino.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}

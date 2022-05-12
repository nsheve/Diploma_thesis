package ru.spbstu.afishakino.service.implement;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.afishakino.entity.Cinema;
import ru.spbstu.afishakino.exception.NotFoundCinemaException;
import ru.spbstu.afishakino.repository.CinemaRepository;
import ru.spbstu.afishakino.service.CinemaService;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public List<Cinema> getAllListCinema() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema findCinemaId(long id) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(id);
        if (optionalCinema.isPresent()) {
            return optionalCinema.get();
        } else {
            throw new NotFoundCinemaException("Not found by id");
        }
    }

    @Override
    public Cinema addCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateCinema(Cinema cinema, long id) {
        Cinema copyCinema = findCinemaId(id);
        BeanUtils.copyProperties(cinema, copyCinema);
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinema(long id) {
        cinemaRepository.delete(findCinemaId(id));
    }

    @Override
    public Cinema findCinemaName(String name) {
        Optional<Cinema> optionalCinema = cinemaRepository.findByName(name);
        if (optionalCinema.isPresent()) {
            return optionalCinema.get();
        } else {
            throw new NotFoundCinemaException("Not found by id");
        }
    }
}

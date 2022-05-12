package ru.spbstu.afishakino.service.implement;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.afishakino.exception.NotFoundFilmException;
import ru.spbstu.afishakino.repository.FilmRepository;
import ru.spbstu.afishakino.entity.Film;
import ru.spbstu.afishakino.service.FilmService;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllListFilm() {
        return filmRepository.findAll();
    }

    @Override
    public Film addFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Film updateFilm(Film film, long id) {
        Film copyFilm = findFilmId(id);
        BeanUtils.copyProperties(film, copyFilm);
        return filmRepository.save(film);
    }

    @Override
    public Film findFilmId(long id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isPresent()) {
            return optionalFilm.get();
        } else {
            throw new NotFoundFilmException("Not found by id");
        }
    }

    @Override
    public void deleteFilm(long id) {
        filmRepository.delete(findFilmId(id));
    }

    @Override
    public Film findFilmTitle(String title) {
        Optional<Film> optionalFilm = filmRepository.findByTitle(title);
        if (optionalFilm.isPresent()) {
            return optionalFilm.get();
        } else {
            throw new NotFoundFilmException("Not found by title");
        }
    }
}

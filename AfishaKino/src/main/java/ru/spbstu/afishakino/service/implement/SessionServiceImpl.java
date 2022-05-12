package ru.spbstu.afishakino.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.afishakino.entity.Session;
import ru.spbstu.afishakino.exception.NotFoundSessionException;
import ru.spbstu.afishakino.repository.SessionRepository;
import ru.spbstu.afishakino.service.SessionService;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> listSession() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findSession(long id) {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if (optionalSession.isPresent()) {
            return optionalSession.get();
        } else {
            throw new NotFoundSessionException("Not found id by Session");
        }
    }

    @Override
    public Session findSessionTime(String time) {
        Optional<Session> optionalSession = sessionRepository.findByTime(time);
        if (optionalSession.isPresent()) {
            return optionalSession.get();
        } else {
            throw new NotFoundSessionException("Not found time Session");
        }
    }
}

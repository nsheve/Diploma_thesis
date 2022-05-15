package ru.spbstu.afishakino.service.implement;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.afishakino.entity.History;
import ru.spbstu.afishakino.exception.NotFoundHistoryException;
import ru.spbstu.afishakino.repository.HistoryRepository;
import ru.spbstu.afishakino.service.HistoryService;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public List<History> listHistory() {
        return (List<History>) historyRepository.findAll();
    }

    @Override
    public History findHistory(long id) {
        Optional<History> optionalHistory = historyRepository.findById(id);
        if (optionalHistory.isPresent()) {
            return optionalHistory.get();
        } else {
            throw new NotFoundHistoryException("Not found!");
        }
    }

    @Override
    public History createHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History updateHistory(History history, long id) {
        History existingHistory = findHistory(id);
        BeanUtils.copyProperties(history, existingHistory);
        return historyRepository.save(existingHistory);
    }

    @Override
    public void deleteHistory(long id) {
        historyRepository.delete(findHistory(id));
    }
}

package dvdrental.sifat.service;

import dvdrental.sifat.entity.FilmsEntity;
import dvdrental.sifat.repository.FilmsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {
    private final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
    @Autowired
    private FilmsRepository filmsRepository;

    @Override
    public Iterable<FilmsEntity> getAllFilms() {
        logger.info("Service layer is processing");
        Iterable<FilmsEntity> all = filmsRepository.findAll();
        logger.info("Iterable is utilized to find all films.");

        return all;
    }
}

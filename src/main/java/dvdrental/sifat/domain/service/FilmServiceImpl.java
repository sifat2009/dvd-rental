package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ResponseStatus;
import dvdrental.sifat.domain.dto.ReturnRequest;
import dvdrental.sifat.domain.entity.*;
import dvdrental.sifat.domain.repository.*;
import dvdrental.sifat.exception.DvdRentalException;
import dvdrental.sifat.validator.RentalValidator;
import dvdrental.sifat.validator.ReturnValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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



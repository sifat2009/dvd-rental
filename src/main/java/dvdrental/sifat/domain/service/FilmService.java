package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ReturnRequest;
import dvdrental.sifat.domain.entity.FilmsEntity;

import java.util.Optional;

public interface FilmService {

    /**
     * The get method will
     * @return all information from films tables.
     */
    Iterable <FilmsEntity> getAllFilms();

}

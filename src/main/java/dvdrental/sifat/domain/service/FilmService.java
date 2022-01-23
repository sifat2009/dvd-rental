package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.FilmRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.entity.FilmsEntity;

public interface FilmService {
    /**
     * The get method will
     * @return all information from films tables.
     */
    Iterable<FilmsEntity> getAllFilms();
    Response<?> rent(FilmRequest rent);
}

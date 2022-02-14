package dvdrental.domain.service;

import dvdrental.domain.entity.FilmsEntity;

public interface FilmService {

    /**
     * The get method will
     * @return all information from films tables.
     */
    Iterable <FilmsEntity> getAllFilms();
    /**
     * The get method will
     * @return all information from films tables about specific film
     */
    FilmsEntity getFilmById(Long id);

}

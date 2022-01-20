package dvdrental.sifat.service;

import dvdrental.sifat.entity.FilmsEntity;

public interface FilmService {
    Iterable<FilmsEntity> getAllFilms();
}

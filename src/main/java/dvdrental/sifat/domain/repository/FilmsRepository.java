package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.FilmsEntity;
import org.springframework.data.repository.CrudRepository;

public interface FilmsRepository extends CrudRepository<FilmsEntity, Long> {
    FilmsEntity findByTitleIgnoreCase(String title);
    FilmsEntity findByFilmId(Long filmId);
}

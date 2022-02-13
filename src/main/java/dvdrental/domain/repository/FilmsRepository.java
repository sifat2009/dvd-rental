package dvdrental.domain.repository;

import dvdrental.domain.entity.FilmsEntity;
import org.springframework.data.repository.CrudRepository;

public interface FilmsRepository extends CrudRepository<FilmsEntity, Long> {
    FilmsEntity findByTitleIgnoreCase(String title);
    FilmsEntity findByFilmId(Long filmId);
}

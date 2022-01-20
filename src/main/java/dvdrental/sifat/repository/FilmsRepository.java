package dvdrental.sifat.repository;

import dvdrental.sifat.entity.FilmsEntity;
import org.springframework.data.repository.CrudRepository;

public interface FilmsRepository extends CrudRepository<FilmsEntity, Long> {
}

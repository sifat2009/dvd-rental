package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.InventoriesEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface InventoriesRepository extends CrudRepository<InventoriesEntity, Long> {
    InventoriesEntity findByLastUpdate(LocalDateTime lastUpdate);
}

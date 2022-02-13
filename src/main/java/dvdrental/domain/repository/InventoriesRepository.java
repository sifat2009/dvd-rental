package dvdrental.domain.repository;

import dvdrental.domain.entity.InventoriesEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoriesRepository extends CrudRepository<InventoriesEntity, Long> {

    InventoriesEntity findByLastUpdate(LocalDateTime lastUpdate);

    InventoriesRepository findByInventoryId(Long inventoryId);

    List<InventoriesEntity> findAllByFilmIdAndStoreIdOrderByLastUpdateDesc(Long filmId, Long storeId);


}

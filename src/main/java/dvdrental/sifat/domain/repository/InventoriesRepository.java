package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.InventoriesEntity;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoriesRepository extends CrudRepository<InventoriesEntity, Long> {

    InventoriesEntity findByLastUpdate(LocalDateTime lastUpdate);

    InventoriesRepository findByInventoryId(Long inventoryId);

    List<InventoriesEntity> findAllByFilmIdAndStoreIdOrderByLastUpdateDesc(Long filmId, Long storeId);


}

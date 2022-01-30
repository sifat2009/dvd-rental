package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.RentalsEntity;
import org.springframework.data.repository.CrudRepository;

public interface RentalsRepository extends CrudRepository<RentalsEntity,Long> {

    RentalsEntity findByInventoryId(Long inventoryId);

}

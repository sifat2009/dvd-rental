package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.RentalsEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface RentalsRepository extends CrudRepository<RentalsEntity,Long> {

    RentalsEntity findByInventoryIdAndCustomerId(Long inventoryId, Long customerId);
    RentalsEntity findByCustomerIdAndRentalDate(Long customerId, LocalDateTime rentalDate);

}

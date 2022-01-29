package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.PaymentsEntities;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository <PaymentsEntities, Long> {
    PaymentsEntities findByRentalId(Long rentalId);
}

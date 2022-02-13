package dvdrental.domain.repository;

import dvdrental.domain.entity.AddressesEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressesRepository extends CrudRepository<AddressesEntity, Long> {
    AddressesEntity findByAddressIgnoreCase(String address);
}

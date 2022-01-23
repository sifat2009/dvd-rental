package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.CustomersEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<CustomersEntity, Long> {
    CustomersEntity findByAddressId(Long addressId);
    CustomersEntity findByFirstNameAndLastNameIgnoreCase
            (String firstName, String lastName);
}

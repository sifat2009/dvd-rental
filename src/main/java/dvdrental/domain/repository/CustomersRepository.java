package dvdrental.domain.repository;

import dvdrental.domain.entity.CustomersEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<CustomersEntity, Long> {
    CustomersEntity findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);
}

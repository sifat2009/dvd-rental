package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.StaffEntity;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<StaffEntity, Long> {

    StaffEntity findByEmailIgnoreCase(String email);

}

package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.StoresEntity;
import org.springframework.data.repository.CrudRepository;

public interface StoresRepository extends CrudRepository<StoresEntity, Long> {

    StoresEntity findByAddressId(Long addressId);

}

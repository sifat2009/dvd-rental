package dvdrental.sifat.domain.repository;

import dvdrental.sifat.domain.entity.AddressesEntity;
import dvdrental.sifat.domain.entity.StoresEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressesRepository extends CrudRepository<AddressesEntity, Long> {
    AddressesEntity findByAddressIgnoreCase(String address);
}

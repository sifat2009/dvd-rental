package dvdrental.sifat.domain.dto;

import dvdrental.sifat.domain.entity.AddressesEntity;
import dvdrental.sifat.domain.entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfo {
    private StaffEntity staff;
    private AddressesEntity storeAddress;
}

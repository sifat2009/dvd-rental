package dvdrental.domain.dto;

import dvdrental.domain.entity.AddressesEntity;
import dvdrental.domain.entity.StaffEntity;
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

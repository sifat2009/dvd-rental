package dvdrental.sifat.domain.dto;

import dvdrental.sifat.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmRequest {
    private FilmsEntity film;
    private StoresEntity store;
    private StaffEntity staff;
    private AddressesEntity address;
    private CustomersEntity customer;
}

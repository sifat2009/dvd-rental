package dvdrental.sifat.domain.dto;

import dvdrental.sifat.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentRequest {
    private FilmsEntity film;
    private StoreInfo store;
    private CustomerInfo customer;
}

package dvdrental.domain.dto;

import dvdrental.domain.entity.FilmsEntity;
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

package dvdrental.sifat.domain.dto;

import dvdrental.sifat.domain.entity.CustomersEntity;
import dvdrental.sifat.domain.entity.FilmsEntity;
import dvdrental.sifat.domain.entity.PaymentsEntities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequest {
    private FilmsEntity film;
    private CustomersEntity customer;
    private PaymentsEntities payment;
}

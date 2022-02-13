package dvdrental.domain.dto;

import dvdrental.domain.entity.CustomersEntity;
import dvdrental.domain.entity.FilmsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequest {
    private FilmsEntity film;
    private CustomersEntity customer;
    private Double payment;
}

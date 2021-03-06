package dvdrental.domain.dto;

import dvdrental.domain.entity.AddressesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {

    private String firstName;
    private String lastName;
    private String email;
    private AddressesEntity customerAddress;
    private Boolean isActive;
}

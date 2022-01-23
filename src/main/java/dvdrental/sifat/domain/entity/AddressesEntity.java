package dvdrental.sifat.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "address")
public class AddressesEntity {
    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressGenerator")
    @SequenceGenerator(name = "addressGenerator", sequenceName = "address_address_id_seq",
           allocationSize = 1)
    private Long addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}

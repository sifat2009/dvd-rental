package dvdrental.sifat.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "customer")
public class CustomersEntity {
    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerGenerator")
    @SequenceGenerator(name = "customerGenerator", sequenceName = "customer_customer_id_seq", initialValue = 1, allocationSize = 1)
    private Long addressId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address_id")
    private Long address_id;

    @Column(name = "activebool")
    private Boolean activebool;

    @Column(name = "create_date")
    private LocalDate create_date;

    @Column(name = "last_update")
    private LocalDateTime last_update;

    @Column(name = "active")
    private Integer active;
}

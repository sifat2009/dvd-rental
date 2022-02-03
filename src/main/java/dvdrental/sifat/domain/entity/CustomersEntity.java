package dvdrental.sifat.domain.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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
    @SequenceGenerator(name = "customerGenerator", sequenceName = "customer_customer_id_seq", allocationSize = 1)
    private Long customerId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "activebool")
    private Boolean activebool;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "active")
    private Integer active;
}

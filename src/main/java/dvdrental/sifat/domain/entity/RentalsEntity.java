package dvdrental.sifat.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rental")
public class RentalsEntity {
    @Column(name = "rental_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rentalGenerator")
    @SequenceGenerator(name = "rentalGenerator", sequenceName = "rental_rental_id_seq", initialValue = 1,
            allocationSize = 1)
    private Long rentalId;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @Column(name = "inventory_id")
    private Long inventoryId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
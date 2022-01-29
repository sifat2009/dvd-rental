package dvdrental.sifat.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class PaymentsEntities {
    @Column(name = "payment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentGenerator")
    @SequenceGenerator(name = "paymentGenerator",sequenceName = "payment_payment_id_seq",allocationSize = 1)
    private Long paymentId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "rental_id")
    private Long rentalId;

    @Column (name = "amount")
    private Double amount;

    @Column (name = "payment_date")
    private LocalDateTime paymentDate;
}

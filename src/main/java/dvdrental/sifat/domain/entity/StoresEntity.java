package dvdrental.sifat.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "store")
public class StoresEntity {
    @Column(name = "store_id")
    @Id
    private Long storeId;

    @Column(name = "manager_staff_id")
    private Long address;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
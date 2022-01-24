package dvdrental.sifat.domain.entity;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "inventory")
public class InventoriesEntity {
    @Column(name = "inventory_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventoryGenerator")
    @SequenceGenerator(name = "inventoryGenerator", sequenceName = "inventory_inventory_id_seq", initialValue = 1,
            allocationSize = 1)
    private Long inventoryId;
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}

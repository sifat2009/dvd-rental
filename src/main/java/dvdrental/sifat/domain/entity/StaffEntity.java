package dvdrental.sifat.domain.entity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "staff")
public class StaffEntity {
    @Column(name = "staff_id")
    @Id
    private Long staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "email")
    private String email;

    @Column(name = "store_id")
    private Long address_id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "picture")
    private Byte [] picture;

}
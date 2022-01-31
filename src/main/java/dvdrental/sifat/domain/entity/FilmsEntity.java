package dvdrental.sifat.domain.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "film")
@DynamicUpdate
public class FilmsEntity {

    @Column(name = "film_id")
    @Id
    private Long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Long releaseYear;

    @Column(name = "language_id")
    private Long languageId;

    @Column(name = "original_language_id")
    private Long originalLanguageId;

    @Column(name = "rental_duration")
    private Long rentalDuration;

    @Column(name = "rental_rate")
    private Double rentalRate;

    @Column(name = "length")
    private Long length;

    @Column(name = "replacement_cost")
    private Double replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "special_features")
    private Byte[] specialFeatures;

    @Column(name = "fulltext")
    private String fulltext;
}

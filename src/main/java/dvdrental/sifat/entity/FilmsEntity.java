package dvdrental.sifat.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "film")
public class FilmsEntity {

    @Column(name = "film_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filmGenerator")
    @SequenceGenerator(name = "filmGenerator", sequenceName = "film_film_id_seq",
            initialValue = 1, allocationSize = 1)
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
    private String originalLanguageId;

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

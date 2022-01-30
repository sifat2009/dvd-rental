package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ReturnRequest;
import dvdrental.sifat.domain.entity.FilmsEntity;

public interface FilmService {
    /**
     * The get method will
     * @return all information from films tables.
     */
    Iterable<FilmsEntity> getAllFilms();

    /**
     *
     * @param rent is an object of type {@code RentRequest}
     * @return an object with response of {@code Response} object with any valid java type
     */
    Response<?> rentADvd(RentRequest rent);
    /**
     *
     * @param rent is an object of type {@code ReturnRequest}
     * @return an object with response of {@code Response} object with any valid java type
     */
    Response<?> returnADvd(ReturnRequest returnRequest);
}

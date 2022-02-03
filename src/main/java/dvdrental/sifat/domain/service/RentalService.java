package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.domain.dto.Response;

public interface RentalService {

    /**
     *
     * @param rent is an object of type {@code RentRequest}
     * @return an object with response of {@code Response} object with any valid java type
     */
    Response<?> rentADvd(RentRequest rent);

}

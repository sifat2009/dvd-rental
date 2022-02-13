package dvdrental.domain.service;

import dvdrental.domain.dto.RentRequest;
import dvdrental.domain.dto.Response;

public interface RentalService {

    /**
     *
     * @param rent is an object of type {@code RentRequest}
     * @return an object with response of {@code Response} object with any valid java type
     */
    Response<?> rentADvd(RentRequest rent);

}

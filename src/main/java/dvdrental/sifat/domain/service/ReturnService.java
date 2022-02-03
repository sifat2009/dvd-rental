package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ReturnRequest;

public interface ReturnService {
    /**
     *
     * @param returnRequest is an object of type {@code ReturnRequest}
     * @return an object with response of {@code Response} object with any valid java type
     */
    Response<?> returnADvd(ReturnRequest returnRequest);

}

package dvdrental.domain.service;

import dvdrental.domain.dto.ReturnRequest;
import dvdrental.domain.dto.Response;

public interface ReturnService {
    /**
     *
     * @param returnRequest is an object of type {@code ReturnRequest}
     * @return an object with response of {@code Response} object with any valid java type
     */
    Response<?> returnADvd(ReturnRequest returnRequest);

}

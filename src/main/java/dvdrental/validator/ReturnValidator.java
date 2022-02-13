package dvdrental.validator;

import dvdrental.domain.dto.ReturnRequest;
import dvdrental.exception.DvdRentalException;
import org.springframework.stereotype.Component;

import static dvdrental.validator.CheckNullOrEmpty.isNullOrEmpty;

@Component
public class ReturnValidator {
    public void validateReturn(ReturnRequest ret) {
        if (ret.getCustomer() == null
                || isNullOrEmpty(ret.getCustomer().getFirstName())
                || isNullOrEmpty(ret.getCustomer().getLastName())) {
            throw new DvdRentalException("Customer first name and last name is required.");
        }
        if (ret.getFilm() == null
                || isNullOrEmpty(ret.getFilm().getTitle())) {
            throw new DvdRentalException("Film title is required.");
        }
    }
}

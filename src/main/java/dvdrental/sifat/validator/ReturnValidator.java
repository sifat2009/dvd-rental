package dvdrental.sifat.validator;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.domain.dto.ReturnRequest;
import dvdrental.sifat.exception.DvdRentalException;
import org.springframework.stereotype.Component;

import static dvdrental.sifat.validator.CheckNullOrEmpty.isNullOrEmpty;

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
        if (isNullOrEmpty(ret.getPayment().toString()))
            throw new DvdRentalException("Payment is required");
    }
}

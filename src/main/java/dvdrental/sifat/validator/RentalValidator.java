package dvdrental.sifat.validator;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.exception.DvdRentalException;
import org.springframework.stereotype.Component;

import static dvdrental.sifat.validator.CheckNullOrEmpty.isNullOrEmpty;

@Component
public class RentalValidator {

    public void validateRental(RentRequest rent) {
        if (rent.getFilm() == null
                || isNullOrEmpty(rent.getFilm().getTitle())) {
            throw new DvdRentalException("Film title is required");
        }
        if (rent.getStore() == null
                || isNullOrEmpty(rent.getStore().getStoreAddress().getAddress())
                || isNullOrEmpty(rent.getStore().getStoreAddress().getCityId().toString())) {
            throw new DvdRentalException("Store address is required");
        }
        if (rent.getCustomer().getCustomerAddress() == null ||
                isNullOrEmpty(rent.getCustomer().getCustomerAddress().getAddress())
                || isNullOrEmpty(rent.getCustomer().getCustomerAddress().getDistrict())
                || rent.getCustomer().getCustomerAddress().getCityId() == null
                || isNullOrEmpty(rent.getCustomer().getCustomerAddress().getPhone())) {
            throw new DvdRentalException("Full address and information " +
                    "must include" + " House #, district, city id, phone #");
        }
        if (rent.getCustomer() == null
                || isNullOrEmpty(rent.getCustomer().getFirstName())
                || isNullOrEmpty(rent.getCustomer().getLastName())
                || isNullOrEmpty(rent.getCustomer().getIsActive().toString())) {
            throw new DvdRentalException("First name, Last name, and active field" +
                    " are required to be filled.");
        }
        if (rent.getStore().getStaff() == null
                || isNullOrEmpty(rent.getCustomer().getEmail())) {
            throw new DvdRentalException("Staff email is required.");
        }
    }
}

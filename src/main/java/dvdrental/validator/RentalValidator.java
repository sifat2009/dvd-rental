package dvdrental.validator;

import dvdrental.domain.dto.RentRequest;
import dvdrental.exception.DvdRentalException;
import org.springframework.stereotype.Component;

@Component
public class RentalValidator {

    public void validateRental(RentRequest rent) {
        if (rent.getFilm() == null
                || CheckNullOrEmpty.isNullOrEmpty(rent.getFilm().getTitle())) {
            throw new DvdRentalException("Film title is required");
        }
        if (rent.getStore() == null
                || CheckNullOrEmpty.isNullOrEmpty(rent.getStore().getStoreAddress().getAddress())
                || CheckNullOrEmpty.isNullOrEmpty(rent.getStore().getStoreAddress().getCityId().toString())) {
            throw new DvdRentalException("Store address is required");
        }
        if (rent.getCustomer().getCustomerAddress() == null ||
                CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getCustomerAddress().getAddress())
                || CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getCustomerAddress().getDistrict())
                || rent.getCustomer().getCustomerAddress().getCityId() == null
                || CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getCustomerAddress().getPhone())) {
            throw new DvdRentalException("Full address and information " +
                    "must include" + " House #, district, city id, phone #");
        }
        if (rent.getCustomer() == null
                || CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getFirstName())
                || CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getLastName())
                || CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getIsActive().toString())) {
            throw new DvdRentalException("First name, Last name, and active field" +
                    " are required to be filled.");
        }
        if (rent.getStore().getStaff() == null
                || CheckNullOrEmpty.isNullOrEmpty(rent.getCustomer().getEmail())) {
            throw new DvdRentalException("Staff email is required.");
        }
    }
}

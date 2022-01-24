package dvdrental.sifat.validator;

import dvdrental.sifat.domain.dto.FilmRequest;
import dvdrental.sifat.exception.DvdRentalException;
import org.springframework.stereotype.Component;

@Component
public class RentalValidator {
    private static boolean isNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }

    public void validateEntry(FilmRequest rent) {
        if (rent.getFilm() == null
                || isNullOrEmpty(rent.getFilm().getTitle())) {
            throw new DvdRentalException("Film title is required");
        }
        if (rent.getStore() == null
                || rent.getStore().getStoreId() == null) {
            throw new DvdRentalException("Store id is required");
        }
        if (rent.getAddress() == null ||
                isNullOrEmpty(rent.getAddress().getAddress())
                || isNullOrEmpty(rent.getAddress().getDistrict())
                || rent.getAddress().getCityId() == null
                || isNullOrEmpty(rent.getAddress().getPhone())) {
            throw new DvdRentalException("Full address and information " +
                    "must include" + " House #, district, city id, phone #");
        }
        if (rent.getCustomer() == null
                || isNullOrEmpty(rent.getCustomer().getFirstName())
                || isNullOrEmpty(rent.getCustomer().getLastName())
                || isNullOrEmpty(rent.getCustomer().getActivebool().toString())) {
            throw new DvdRentalException("First name, Last name, and active field" +
                    " are required to be filled.");
        }
        if (rent.getStaff() == null
                || isNullOrEmpty(rent.getCustomer().getEmail())) {
            throw new DvdRentalException("Staff email is required.");
        }
    }
}

package dvdrental.domain.service;

import dvdrental.domain.dto.RentRequest;
import dvdrental.domain.dto.ResponseStatus;
import dvdrental.domain.entity.*;
import dvdrental.domain.dto.Response;
import dvdrental.domain.repository.*;
import dvdrental.exception.DvdRentalException;
import dvdrental.validator.RentalValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RentalServiceImpl implements RentalService {

    private final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
    @Autowired
    private FilmsRepository filmsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private InventoriesRepository inventoriesRepository;

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private RentalValidator rentalValidator;

    @Override
    public Response<?> rentADvd(RentRequest rent) {
        try {

            rentalValidator.validateRental(rent);

            FilmsEntity filmsEntity = filmsRepository.findByTitleIgnoreCase(rent.getFilm().getTitle());
            logger.info("Film title is processed. FilmId: " + filmsEntity.getFilmId());

            if (filmsEntity == null) {
                throw new DvdRentalException("Film does not exist in the library");
            }

            AddressesEntity storeAddress = addressesRepository.findByAddressIgnoreCase
                    (rent.getStore().getStoreAddress().getAddress());

            StoresEntity storesEntity = storesRepository.findByAddressId(storeAddress.getAddressId());
            logger.info("Store is being processed.");

            if (storesEntity == null) {
                throw new DvdRentalException("Store does not exist");
            }
            logger.info("Store was processed. StoreId: " + storesEntity.getStoreId());
            StaffEntity staffEntity =
                    staffRepository.findByEmailIgnoreCase(rent.getStore().getStaff().getEmail());

            if (staffEntity == null) {
                throw new DvdRentalException("Invalid staff email address.");
            }
            logger.info("Staff email is processed. StaffId: " + staffEntity.getStaffId());

            AddressesEntity customerAddress = addressesRepository.findByAddressIgnoreCase
                    (rent.getCustomer().getCustomerAddress().getAddress());
            logger.info("Address is being processed");

            CustomersEntity customerEntity =
                    customersRepository.findByFirstNameAndLastNameIgnoreCase(rent.getCustomer()
                            .getFirstName(), rent.getCustomer().getLastName());
            //Address does not exist.
            if (customerAddress == null) {
                if (customerEntity == null) {
                    customerAddress = getAddressesEntity(rent);
                    customerAddress = addressesRepository.save(customerAddress);
                    logger.info("Address table was updated.");
                    //Setting up customer data.
                    customerEntity = getCustomersEntity
                            (rent, storesEntity.getStoreId(), customerAddress.getAddressId());
                    customerEntity = customersRepository.save(customerEntity);
                    logger.info("Customer was processed. CustomerId: " + customerEntity.getCustomerId());
                }
            }
            //Address exists
            if (customerEntity == null) {
                customerEntity = getCustomersEntity
                        (rent, storesEntity.getStoreId(), customerAddress.getAddressId());
                customerEntity = customersRepository.save(customerEntity);
                logger.info("Customer was processed. CustomerId: " + customerEntity.getCustomerId());
            }
            logger.info("Customer table was updated.");
            logger.info("Address was processed. AddressId: " + customerAddress.getAddressId());

            InventoriesEntity inventoriesEntity =
                    getInventoriesEntity(filmsEntity.getFilmId(), storesEntity.getStoreId());
            inventoriesEntity = inventoriesRepository.save(inventoriesEntity);
            logger.info("Added new inventory. InventoryId: " + inventoriesEntity.getInventoryId());

            RentalsEntity rentalsEntity = getRentalsEntity
                    (staffEntity.getStaffId(), customerEntity.getCustomerId(), inventoriesEntity.getInventoryId());
            rentalsEntity = rentalsRepository.save(rentalsEntity);
            logger.info("Rental was processed. RentalId: " + rentalsEntity.getRentalId());

            return new Response<>(ResponseStatus.SUCCESS, customerEntity);

        } catch (DvdRentalException e) {
            logger.error("Fatal Exception", e);

            return new Response<>(ResponseStatus.ERROR, e.getMessage());

        } catch (Exception e) {

            logger.error("Fatal Exception", e);

            return new Response<>(ResponseStatus.ERROR, "Unknown exception happened." +
                    "Please contact customer service!");
        }
    }

    private RentalsEntity getRentalsEntity(Long staffId, Long customerId, Long inventoryId) {
        RentalsEntity rentalsEntity = new RentalsEntity();
        logger.info("Processing rental");
        rentalsEntity.setRentalDate(LocalDateTime.now());
        rentalsEntity.setInventoryId(inventoryId);
        rentalsEntity.setCustomerId(customerId);
        rentalsEntity.setReturnDate(null);
        rentalsEntity.setStaffId(staffId);
        rentalsEntity.setLastUpdate(LocalDateTime.now());
        return rentalsEntity;
    }

    private InventoriesEntity getInventoriesEntity(Long filmId, Long storeId) {
        InventoriesEntity inventoriesEntity = new InventoriesEntity();
        inventoriesEntity.setFilmId(filmId);
        inventoriesEntity.setStoreId(storeId);
        inventoriesEntity.setLastUpdate(LocalDateTime.now());
        return inventoriesEntity;
    }

    private CustomersEntity getCustomersEntity(RentRequest rent, Long storeId, Long addressId) {
        CustomersEntity customerEntity;
        customerEntity = new CustomersEntity();
        customerEntity.setFirstName(rent.getCustomer().getFirstName());
        customerEntity.setLastName(rent.getCustomer().getLastName());
        customerEntity.setEmail(rent.getCustomer().getEmail());
        customerEntity.setActivebool(rent.getCustomer().getIsActive());
        customerEntity.setCreateDate(LocalDate.now());
        customerEntity.setActive(rent.getCustomer().getIsActive() ? 1 : 0);
        customerEntity.setStoreId(storeId);
        customerEntity.setAddressId(addressId);
        customerEntity.setLastUpdate(LocalDateTime.now());
        return customerEntity;
    }

    private AddressesEntity getAddressesEntity(RentRequest rent) {
        AddressesEntity customerAddress;
        customerAddress = new AddressesEntity();
        customerAddress.setAddress(rent.getCustomer().getCustomerAddress().getAddress());
        customerAddress.setAddress2(rent.getCustomer().getCustomerAddress().getAddress2());
        customerAddress.setDistrict(rent.getCustomer().getCustomerAddress().getDistrict());
        customerAddress.setCityId(rent.getCustomer().getCustomerAddress().getCityId());
        customerAddress.setPostalCode(rent.getCustomer().getCustomerAddress().getPostalCode());
        customerAddress.setPhone(rent.getCustomer().getCustomerAddress().getPhone());
        customerAddress.setLastUpdate(LocalDateTime.now());
        return customerAddress;
    }
}

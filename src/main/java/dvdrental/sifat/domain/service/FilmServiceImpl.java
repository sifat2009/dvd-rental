package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.RentRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ResponseStatus;
import dvdrental.sifat.domain.dto.ReturnRequest;
import dvdrental.sifat.domain.entity.*;
import dvdrental.sifat.domain.repository.*;
import dvdrental.sifat.exception.DvdRentalException;
import dvdrental.sifat.validator.RentalValidator;
import dvdrental.sifat.validator.ReturnValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class FilmServiceImpl implements FilmService {

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

    @Autowired
    private ReturnValidator returnValidator;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public Iterable<FilmsEntity> getAllFilms() {

        logger.info("Service layer is processing");
        Iterable<FilmsEntity> all = filmsRepository.findAll();
        logger.info("Iterable is utilized to find all films.");
        return all;
    }

    @Override
    public Response<?> rentADvd(RentRequest rent) {
        try {

            rentalValidator.validateRental(rent);

            FilmsEntity filmsEntity = filmsRepository.findByTitleIgnoreCase(rent.getFilm().getTitle());
            logger.info("Film title is processed.");

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

            StaffEntity staffEntity = staffRepository.findByEmailIgnoreCase(rent.getStore().getStaff().getEmail());

            if (staffEntity == null) {
                throw new DvdRentalException("Invalid staff email address.");
            }
            logger.info("Staff email is processed.");

            AddressesEntity customerAddress = addressesRepository.findByAddressIgnoreCase
                    (rent.getCustomer().getCustomerAddress().getAddress());
            logger.info("Address is being processed");

            CustomersEntity customerEntity =
                    customersRepository.findByFirstNameAndLastNameIgnoreCase(rent.getCustomer()
                            .getFirstName(), rent.getCustomer().getLastName());
            //Address does not exist.
            if (customerAddress == null) {
                if (customerEntity == null) {
                    customerAddress = new AddressesEntity();
                    customerAddress.setAddress(rent.getCustomer().getCustomerAddress().getAddress());
                    customerAddress.setAddress2(rent.getCustomer().getCustomerAddress().getAddress2());
                    customerAddress.setDistrict(rent.getCustomer().getCustomerAddress().getDistrict());
                    customerAddress.setCityId(rent.getCustomer().getCustomerAddress().getCityId());
                    customerAddress.setPostalCode(rent.getCustomer().getCustomerAddress().getPostalCode());
                    customerAddress.setPhone(rent.getCustomer().getCustomerAddress().getPhone());
                    customerAddress.setLastUpdate(LocalDateTime.now());
                    customerAddress = addressesRepository.save(customerAddress);
                    logger.info("Address table was updated.");
                    //Setting up customer data.
                    customerEntity = new CustomersEntity();
                    customerEntity.setFirstName(rent.getCustomer().getFirstName());
                    customerEntity.setLastName(rent.getCustomer().getLastName());
                    customerEntity.setEmail(rent.getCustomer().getEmail());
                    customerEntity.setActivebool(rent.getCustomer().getIsActive());
                    customerEntity.setCreateDate(LocalDate.now());
                    customerEntity.setActive(rent.getCustomer().getIsActive() ? 1 : 0);
                    customerEntity.setStoreId(storesEntity.getStoreId());
                    customerEntity.setAddressId(customerAddress.getAddressId());
                    customerEntity.setLastUpdate(LocalDateTime.now());
                    customerEntity = customersRepository.save(customerEntity);
                    logger.info("Customer was processed");
                }
            }
            //Address exists
            if (customerEntity == null) {
                customerEntity = new CustomersEntity();
                customerEntity.setFirstName(rent.getCustomer().getFirstName());
                customerEntity.setLastName(rent.getCustomer().getLastName());
                customerEntity.setEmail(rent.getCustomer().getEmail());
                customerEntity.setActivebool(rent.getCustomer().getIsActive());
                customerEntity.setCreateDate(LocalDate.now());
                customerEntity.setActive(rent.getCustomer().getIsActive() ? 1 : 0);
                customerEntity.setStoreId(storesEntity.getStoreId());
                customerEntity.setAddressId(customerAddress.getAddressId());
                customerEntity.setLastUpdate(LocalDateTime.now());
                customerEntity = customersRepository.save(customerEntity);
                logger.info("Customer was processed");
            }
            logger.info("Customer table was updated.");

            InventoriesEntity inventoriesEntity = new InventoriesEntity();
            inventoriesEntity.setFilmId(filmsEntity.getFilmId());
            inventoriesEntity.setStoreId(storesEntity.getStoreId());
            inventoriesEntity.setLastUpdate(LocalDateTime.now());
            inventoriesEntity = inventoriesRepository.save(inventoriesEntity);
            logger.info("Added new inventory");
            RentalsEntity rentalsEntity = new RentalsEntity();
            logger.info("Processing rental");
            rentalsEntity.setRentalDate(LocalDateTime.now());
            rentalsEntity.setInventoryId(inventoriesEntity.getInventoryId());
            rentalsEntity.setCustomerId(customerEntity.getCustomerId());
            rentalsEntity.setReturnDate(null);
            rentalsEntity.setStaffId(staffEntity.getStaffId());
            rentalsEntity.setLastUpdate(LocalDateTime.now());
            rentalsEntity = rentalsRepository.save(rentalsEntity);
            logger.info("Rental was processed");

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

    @Override
    public Response<?> returnADvd(ReturnRequest returnRequest) {

        try {
            returnValidator.validateReturn(returnRequest);
            FilmsEntity filmsEntity = filmsRepository
                    .findByTitleIgnoreCase(returnRequest.getFilm().getTitle());
            logger.info("Film is being processed.");

            if (filmsEntity == null) {
                throw new DvdRentalException(" Please enter a valid film title");
            }
            logger.info("Film table was processed.");
            CustomersEntity customer = customersRepository
                    .findByFirstNameAndLastNameIgnoreCase
                            (returnRequest.getCustomer().getFirstName()
                                    , returnRequest.getCustomer().getLastName());
            logger.info("Customer is being processed.");

            if (customer == null) {
                throw new DvdRentalException(" Please provide valid name.");
            }
            logger.info("Customer was found.");

            InventoriesEntity inventoriesEntity = inventoriesRepository
                    .findByFilmIdAndLastUpdateContaining(filmsEntity.getFilmId(),
                            customer.getLastUpdate());
            logger.info("Inventory is being processed.");

            if (inventoriesEntity == null) {
                throw new DvdRentalException(" Please contact customer service.");
            }
            logger.info("Inventory was found.");

            RentalsEntity rentalsEntity = rentalsRepository
                    .findByInventoryId(inventoriesEntity.getInventoryId());
            logger.info("Rental is being processed.");

            if (rentalsEntity == null) {
                throw new DvdRentalException("Please contact customer service.");
            }

            PaymentsEntities paymentsEntities = new PaymentsEntities();
            paymentsEntities.setCustomerId(customer.getCustomerId());
            paymentsEntities.setStaffId(rentalsEntity.getStaffId());
            paymentsEntities.setPaymentDate(LocalDateTime.now());
            paymentsEntities.setRentalId(rentalsEntity.getRentalId());
            paymentsEntities.setAmount(returnRequest.getPayment().getAmount());
            paymentsEntities = paymentsRepository.save(paymentsEntities);

            rentalsEntity.setReturnDate(LocalDateTime.now());
            rentalsEntity = rentalsRepository.save(rentalsEntity);

            return new Response<>(ResponseStatus.SUCCESS, returnRequest);

        } catch (DvdRentalException e) {
            logger.error("Fatal Exception", e);

            return new Response<>(ResponseStatus.ERROR, e.getMessage());

        } catch (Exception e) {
            logger.error("Fatal Exception", e);
            return new Response<>(ResponseStatus.ERROR, "Unknown exception happened." +
                    "Please contact customer service!");
        }
    }
}



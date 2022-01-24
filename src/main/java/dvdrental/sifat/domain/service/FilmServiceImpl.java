package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.FilmRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ResponseStatus;
import dvdrental.sifat.domain.entity.*;
import dvdrental.sifat.domain.repository.*;
import dvdrental.sifat.exception.DvdRentalException;
import dvdrental.sifat.validator.RentalValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Iterable<FilmsEntity> getAllFilms() {

        logger.info("Service layer is processing");
        Iterable<FilmsEntity> all = filmsRepository.findAll();
        logger.info("Iterable is utilized to find all films.");
        return all;
    }

    @Override
    public Response<?> rent(FilmRequest rent) {
        try {
            rentalValidator.validateEntry(rent);

            FilmsEntity filmsEntity = filmsRepository.findByTitleIgnoreCase(rent.getFilm().getTitle());
            logger.info("Film title is processed.");
            if (filmsEntity == null) {
                throw new DvdRentalException("Film does not exist in the library");
            }

            StoresEntity storesEntity = storesRepository.findByStoreId(rent.getStore().getStoreId());
            logger.info("Store is being processed.");
            if (storesEntity == null) {
                throw new DvdRentalException("Store does not exist");
            }

            StaffEntity staffEntity = staffRepository.findByEmailIgnoreCase(rent.getStaff().getEmail());
            if (staffEntity == null) {
                throw new DvdRentalException("Invalid staff email address.");
            }
            logger.info("Staff email is processed.");
            AddressesEntity customerAddress = addressesRepository.findByAddressIgnoreCase
                    (rent.getAddress().getAddress());
            logger.info("Address is being processed");
            CustomersEntity customerEntity =
                    customersRepository.findByFirstNameAndLastNameIgnoreCase(rent.getCustomer()
                            .getFirstName(), rent.getCustomer().getLastName());
            //Address does not exist.
            if (customerAddress == null) {
                if (customerEntity == null) {
                    customerAddress = new AddressesEntity();
                    customerAddress.setAddress(rent.getAddress().getAddress());
                    customerAddress.setAddress2(rent.getAddress().getAddress2());
                    customerAddress.setDistrict(rent.getAddress().getDistrict());
                    customerAddress.setCityId(rent.getAddress().getCityId());
                    customerAddress.setPostalCode(rent.getAddress().getPostalCode());
                    customerAddress.setPhone(rent.getAddress().getPhone());
                    customerAddress.setLastUpdate(LocalDateTime.now());
                    customerAddress = addressesRepository.save(customerAddress);
                    logger.info("Address table was updated.");
                    //Setting up customer data.
                    customerEntity = new CustomersEntity();
                    customerEntity.setFirstName(rent.getCustomer().getFirstName());
                    customerEntity.setLastName(rent.getCustomer().getLastName());
                    customerEntity.setEmail(rent.getCustomer().getEmail());
                    customerEntity.setActivebool(rent.getCustomer().getActivebool());
                    customerEntity.setCreateDate(LocalDate.now());
                    customerEntity.setActive(rent.getCustomer().getActive());
                    customerEntity.setStoreId(rent.getStore().getStoreId());
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
                customerEntity.setActivebool(rent.getCustomer().getActivebool());
                customerEntity.setCreateDate(LocalDate.now());
                customerEntity.setActive(rent.getCustomer().getActive());
                customerEntity.setStoreId(rent.getStore().getStoreId());
                customerEntity.setAddressId(customerAddress.getAddressId());
                customerEntity.setLastUpdate(LocalDateTime.now());
                customerEntity = customersRepository.save(customerEntity);
                logger.info("Customer was processed");
            }
            InventoriesEntity inventoriesEntity = inventoriesRepository
                    .findByLastUpdate(rent.getFilm().getLastUpdate());
            logger.info("Inventory of film is being processed.");
            if (inventoriesEntity == null) {
                inventoriesEntity = new InventoriesEntity();
                inventoriesEntity.setInventoryId(inventoriesEntity.getInventoryId());
                inventoriesEntity.setFilmId(filmsEntity.getFilmId());
                inventoriesEntity.setStoreId(storesEntity.getStoreId());
                inventoriesEntity.setLastUpdate(filmsEntity.getLastUpdate());
                inventoriesEntity = inventoriesRepository.save(inventoriesEntity);
                logger.info("Added new inventory");
            }
            RentalsEntity rentalsEntity = new RentalsEntity();
            logger.info("Processing rental");
            rentalsEntity.setRentalDate(LocalDateTime.now());
            rentalsEntity.setInventoryId(inventoriesEntity.getInventoryId());
            rentalsEntity.setCustomerId(customerEntity.getCustomerId());
            rentalsEntity.setReturnDate(null);
            rentalsEntity.setStaffId(staffEntity.getStaffId());
            rentalsEntity.setLastUpdate(LocalDateTime.now());
            rentalsEntity = rentalsRepository.save(rentalsEntity);

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
}

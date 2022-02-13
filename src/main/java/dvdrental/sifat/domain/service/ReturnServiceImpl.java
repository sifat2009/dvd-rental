package dvdrental.sifat.domain.service;

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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

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
    private ReturnValidator returnValidator;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public Response<?> returnADvd(ReturnRequest returnRequest) {

        returnValidator.validateReturn(returnRequest);
        FilmsEntity filmsEntity = filmsRepository
                .findByTitleIgnoreCase(returnRequest.getFilm().getTitle());
        logger.info("Film is being processed.");

        if (filmsEntity == null) {
            throw new DvdRentalException(" Please enter a valid film title");
        }
        logger.info("Film table was processed. FilmId: " + filmsEntity.getFilmId());
        CustomersEntity customer = customersRepository
                .findByFirstNameAndLastNameIgnoreCase
                        (returnRequest.getCustomer().getFirstName()
                                , returnRequest.getCustomer().getLastName());
        logger.info("Customer is being processed.");

        if (customer == null) {
            throw new DvdRentalException(" Please provide valid name.");
        }
        logger.info("Customer is valid. CustomerId: " + customer.getCustomerId());

        List<InventoriesEntity> inventoriesEntity = inventoriesRepository
                .findAllByFilmIdAndStoreIdOrderByLastUpdateDesc(filmsEntity.getFilmId(), customer.getStoreId());
        logger.info("Inventory is being processed");

        if (inventoriesEntity == null) {
            throw new DvdRentalException(" Please contact customer service.");
        }
        logger.info("Inventory exists.InventoriesId are "
                + inventoriesEntity);

        RentalsEntity rentalsEntity = rentalsRepository
                .findByInventoryIdAndCustomerId
                        (inventoriesEntity.get(0).getInventoryId(), customer.getCustomerId());
        logger.info("Rental is being processed.");

        if (rentalsEntity == null) {
            throw new DvdRentalException("Please contact customer service.");
        }/*
        PaymentsEntities paymentsEntities
                = getPaymentsEntities(returnRequest, customer.getCustomerId(), rentalsEntity);
        paymentsEntities = paymentsRepository.save(paymentsEntities);
        logger.info("Payment table was updated.");
        */
        if (rentalsEntity.getReturnDate() == null) {
            rentalsEntity.setReturnDate(LocalDateTime.now());
            rentalsEntity = rentalsRepository.save(rentalsEntity);
            logger.info("Rental was updated. RentalId: " + rentalsEntity.getRentalId());
        } else {
            throw new DvdRentalException("Please contact customer service.");
        }
        return new Response<>(ResponseStatus.SUCCESS, rentalsEntity);
    }

    private PaymentsEntities getPaymentsEntities(ReturnRequest returnRequest, Long customerId, RentalsEntity rentalsEntity) {
        PaymentsEntities paymentsEntities = new PaymentsEntities();
        paymentsEntities.setCustomerId(customerId);
        paymentsEntities.setStaffId(rentalsEntity.getStaffId());
        paymentsEntities.setRentalId(rentalsEntity.getRentalId());
        paymentsEntities.setAmount(returnRequest.getPayment());
        logger.info("Payment is processing.");
        paymentsEntities.setPaymentDate(LocalDateTime.now());
        return paymentsEntities;
    }
}

package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.dto.FilmRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.entity.*;
import dvdrental.sifat.domain.repository.*;
import dvdrental.sifat.exception.DvdRentalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Iterable<FilmsEntity> getAllFilms() {
        logger.info("Service layer is processing");
        Iterable<FilmsEntity> all = filmsRepository.findAll();
        logger.info("Iterable is utilized to find all films.");

        return all;
    }

    @Override
    public Response<?> rent(FilmRequest rent) {
        FilmsEntity filmsEntity = filmsRepository.findByTitleIgnoreCase(rent.getFilm().getTitle());
        if(filmsEntity == null) {
            throw new DvdRentalException("Film does not exist in the library");
        }
        AddressesEntity addressesEntity = addressesRepository.
                findByAddress(rent.getAddress().getAddress());
        if(addressesEntity == null) {
            throw new DvdRentalException("Invalid store address");
        }
        return null;
    }
}

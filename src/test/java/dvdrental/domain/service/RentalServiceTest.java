package dvdrental.domain.service;

import dvdrental.domain.repository.*;
import dvdrental.domain.entity.*;
import dvdrental.exception.DvdRentalException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    @InjectMocks
    RentalServiceImpl rentalService = new RentalServiceImpl();
    @Mock
    private CustomersRepository customersRepository;
    @Mock
    private AddressesRepository addressesRepository;
    @Mock
    private FilmsRepository filmsRepository;
    @Mock
    private InventoriesRepository inventoriesRepository;
    @Mock
    private StaffRepository staffRepository;
    @Mock
    private StoresRepository storesRepository;
    @Mock
    private RentalsRepository rentalsRepository;

    @Test
    void rentADvd() {
    }

    @Test
    public void responseIsNotNull() {
        /*
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenReturn(getFilmEntities());
        Mockito.when(customersRepository.findById(Mockito.anyLong())).thenReturn(getFilmEntities());
        Mockito.when(addressesRepository.findById(Mockito.anyLong())).thenReturn(getFilmEntities());
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenReturn(getFilmEntities());
         */
    }
    @Test
    public void responseIsNull() {
        //Mock repo to return empty
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(customersRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(addressesRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(staffRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(inventoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(rentalsRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
    }
    @Test
    public void unknownExceptionTest() {
        //Mock repo to throw Dvd-rental Exception and
        Mockito.when((filmsRepository.findById(Mockito.anyLong()))).thenThrow(DvdRentalException.class);
        Mockito.when(staffRepository.findByEmailIgnoreCase
                (Mockito.anyString())).thenThrow(DvdRentalException.class);
        Mockito.when(addressesRepository.findByAddressIgnoreCase(Mockito.anyString()))
                .thenThrow(DvdRentalException.class);
        Mockito.when(customersRepository.findByFirstNameAndLastNameIgnoreCase
                (Mockito.anyString(), Mockito.anyString())).thenThrow(DvdRentalException.class);
        /*
        Assertions.assertThrows(DvdRentalException.class, ()-> rentalService.rentADvd
                (getFirstAndLastName(),getFilmById(),getStaffByEmail()
                , getStoreByAddress()));
         */
    }
}
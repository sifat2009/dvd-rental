package dvdrental.sifat.domain.service;

import dvdrental.sifat.domain.entity.*;
import dvdrental.sifat.domain.repository.*;
import dvdrental.sifat.exception.DvdRentalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

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

    @InjectMocks
    RentalServiceImpl rentalService = new RentalServiceImpl();
    @Test
    void rentADvd() {
    }
    @Test
    public void responseIsNotNull() {

    }
    @Test
    public void responseIsNull() {
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(customersRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(addressesRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(staffRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    /*
        ResponseEntity response = rentalService.rentADvd(getFirstAndLastName(),getFilmById(),getStaffByEmail()
        , getStoreByAddress());
    */
    }

    @Test
    public void unknownExceptionTest(){
        Mockito.when((filmsRepository.findByFilmId(Mockito.anyLong()))).thenThrow(DvdRentalException.class);
        Mockito.when(staffRepository.findByEmailIgnoreCase
                (Mockito.anyString())).thenThrow(DvdRentalException.class);
        Mockito.when(addressesRepository.findByAddressIgnoreCase(Mockito.anyString()))
                .thenThrow(DvdRentalException.class);
        Mockito.when(customersRepository.findByFirstNameAndLastNameIgnoreCase
                (Mockito.anyString(),Mockito.anyString())).thenThrow(DvdRentalException.class);
    }

    private Optional<CustomersEntity> getFirstAndLastName() {
        CustomersEntity customersEntity = new CustomersEntity();
        customersEntity.setFirstName("Johnny");
        customersEntity.setLastName("Black");
        customersEntity.setEmail("johnnyblack701@gmail.com");
        AddressesEntity byAddressIgnoreCase = addressesRepository.findByAddressIgnoreCase("123 new lots");
        customersEntity.setAddressId(byAddressIgnoreCase.getAddressId());
        return Optional.of(customersEntity);
    }

    private Optional<AddressesEntity> getAddressById() {
        AddressesEntity addressesEntity = new AddressesEntity();
        addressesEntity.setAddress("134 Brooklyn street");
        return Optional.of(addressesEntity);
    }
    private Optional<StaffEntity> getStaffByEmail() {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setEmail("johnnyblack@gmail.com");
        return Optional.of(staffEntity);
    }

    private Optional <FilmsEntity> getFilmById() {
        FilmsEntity filmsEntity = new FilmsEntity();
        filmsEntity.setTitle("New World");
        return Optional.of(filmsEntity);
    }
    private Optional <StoresEntity> getStoreByAddress(){
        StoresEntity storesEntity = new StoresEntity();
        AddressesEntity byAddressIgnoreCase = addressesRepository.findByAddressIgnoreCase("123 lots ave");
        storesEntity.setManagerStaffId(byAddressIgnoreCase.getAddressId());
        return Optional.of(storesEntity);
    }
}
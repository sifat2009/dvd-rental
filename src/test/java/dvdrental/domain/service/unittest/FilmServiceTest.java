package dvdrental.domain.service.unittest;

import dvdrental.domain.entity.FilmsEntity;
import dvdrental.domain.repository.FilmsRepository;
import dvdrental.domain.service.FilmServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {
    @Mock
    private FilmsRepository filmsRepository;

    @InjectMocks
    FilmServiceImpl filmService = new FilmServiceImpl();
    @Test
    public void findAllCustomers() {
        //Since it is a unit test, we need to bypass repository layer.
        Mockito.when(filmsRepository.findAll()).thenReturn(getAllFilms());
        FilmsEntity next = filmService.getAllFilms().iterator().next();
        Assertions.assertEquals(355L,next.getFilmId());
        Assertions.assertEquals("Jurassic World",next.getTitle());
    }



    @Test
    public void responseNotNull(){
        //System.out.println("Test 2");
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenReturn(getFilmById());
        FilmsEntity actual = filmService.getFilmById(355L);
        assertNotNull(actual);
        assertEquals("Jurassic World",actual.getTitle());
        assertNotNull(actual.getTitle());
    }

    private Iterable<FilmsEntity> getAllFilms() {
        FilmsEntity filmsEntity = new FilmsEntity();
        filmsEntity.setFilmId(355l);
        filmsEntity.setTitle("Jurassic World");
        List<FilmsEntity> filmsEntitiesList = new ArrayList<>();
       filmsEntitiesList.add(filmsEntity);
        return filmsEntitiesList;
    }
    private Optional<FilmsEntity> getFilmById() {
        FilmsEntity filmsEntity = new FilmsEntity();
        filmsEntity.setFilmId(12L);
        filmsEntity.setTitle("Jurassic World");

        return Optional.of(filmsEntity);
    }

    @Test
    public void responseIsNull(){
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        FilmsEntity customersEntity = filmService.getFilmById(355L);
        assertNull(customersEntity);
    }
    @Test
    public void responseUnknownException(){
        Mockito.when(filmsRepository.findById(Mockito.anyLong())).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> filmService.getFilmById(355L));
    }
}
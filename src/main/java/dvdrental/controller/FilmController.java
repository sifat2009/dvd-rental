package dvdrental.controller;

import dvdrental.domain.dto.Response;
import dvdrental.domain.dto.ResponseStatus;
import dvdrental.domain.entity.FilmsEntity;
import dvdrental.domain.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/film")
@Slf4j
public class FilmController {

    private final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllFilms() {

        Iterable<FilmsEntity> allFilms = filmService.getAllFilms();
        logger.info("Request was accepted for get all films");
        Response<Iterable<FilmsEntity>> response = new Response<>();
        logger.info("Empty response object is created");
        response.setResponseStatus(ResponseStatus.SUCCESS);
        logger.info("Response status is set");
        response.setResult(allFilms);
        logger.info("Request call result is set");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id) {
        FilmsEntity film = filmService.getFilmById(id);
        if (film == null) {
            Response<String> response = new Response<>();
            response.setResult("No Data Found");
            response.setResponseStatus(ResponseStatus.WARNING);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        //We have data.
        Response<FilmsEntity> filmsEntityResponse = new Response<>();
        filmsEntityResponse.setResult(film);
        filmsEntityResponse.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(filmsEntityResponse, HttpStatus.OK);
    }
}

package dvdrental.sifat.controller;

import dvdrental.sifat.dto.Response;
import dvdrental.sifat.dto.ResponseStatus;
import dvdrental.sifat.entity.FilmsEntity;
import dvdrental.sifat.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dvd-rental")
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
}

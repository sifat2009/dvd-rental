package dvdrental.sifat.controller;

import dvdrental.sifat.domain.dto.FilmRequest;
import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ResponseStatus;
import dvdrental.sifat.domain.entity.FilmsEntity;
import dvdrental.sifat.domain.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dvd")
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
    @PostMapping("/rent")
    public ResponseEntity<?> submitRental(@RequestBody FilmRequest request) {

        Response<?> response = filmService.rent(request);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

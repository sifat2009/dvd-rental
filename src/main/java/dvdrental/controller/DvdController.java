package dvdrental.controller;

import dvdrental.domain.dto.ReturnRequest;
import dvdrental.domain.dto.RentRequest;
import dvdrental.domain.dto.Response;
import dvdrental.domain.service.RentalService;
import dvdrental.domain.service.ReturnService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dvd")
@Slf4j
public class DvdController {

    private final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    RentalService rentalService;

    @Autowired
    ReturnService returnService;

    @PostMapping("/rent")
    public ResponseEntity<?> RentDvd(@RequestBody RentRequest request) {

        Response<?> response = rentalService.rentADvd(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> ReturnDvd(@RequestBody ReturnRequest request) {

        Response<?> response = returnService.returnADvd(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

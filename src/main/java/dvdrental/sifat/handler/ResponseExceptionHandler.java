package dvdrental.sifat.handler;

import dvdrental.sifat.domain.dto.Response;
import dvdrental.sifat.domain.dto.ResponseStatus;
import dvdrental.sifat.exception.DvdRentalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
//Handles all exceptions.
@Slf4j
public class ResponseExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception exception, WebRequest webRequest) {

        Response<String> stringResponse;

        if (exception instanceof DvdRentalException) {
            stringResponse = new Response<>(ResponseStatus.ERROR, exception.getMessage());
        } else {
            stringResponse = new Response<>(ResponseStatus.ERROR, "Unknown Exception occurred." +
                    "Please contact customer service!");
        }
        return new ResponseEntity<>(stringResponse, HttpStatus.OK);
    }
}

package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.advice;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.BookingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class FFSystemWebAPIExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePublisherNotFoundException(BookingNotFoundException bookingNotFoundException) {
        Map<String , String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("errorMessage", bookingNotFoundException.getMessage());
        return errorMessageMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleDataValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errorMap = new HashMap<String, String>();
        methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errorMap.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );
        return errorMap;
    }
}

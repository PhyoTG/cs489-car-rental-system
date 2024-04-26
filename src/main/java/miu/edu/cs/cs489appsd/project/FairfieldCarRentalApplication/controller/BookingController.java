package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.BookingNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ffweb/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{bookingId}")
    public ResponseEntity<BookingResponse2> getBooking(@PathVariable int bookingId) throws BookingNotFoundException {
        BookingResponse2 response = bookingService.getBooking(bookingId);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{bookingId}")
    public ResponseEntity<BookingResponse2> updateBooking(@PathVariable int bookingId,
                                                 @RequestBody BookingRequest request) throws BookingNotFoundException {
        BookingResponse2 response = bookingService.updateBooking(bookingId, request);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<BookingResponse2>> getAllBookings() {
        List<BookingResponse2> responses = bookingService.getAllBookings();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}

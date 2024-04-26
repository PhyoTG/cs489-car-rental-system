package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service;


import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.CostResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.BookingNotFoundException;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    BookingResponse2 getBooking(Integer BookingId) throws BookingNotFoundException;
    BookingResponse2 updateBooking(Integer BookingId, BookingRequest request) throws BookingNotFoundException ;
    void deleteBooking(Integer BookingId);
    List<BookingResponse2> getAllBookings();

    List<CostResponse> calculateCost();
}

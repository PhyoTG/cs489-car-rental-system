package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Booking;

public record CustomerResponse2(Integer customerId,
                                String firstName,
                                String lastName,
                                String contactPhone,
                                String email,
                                BookingResponse booking) {
}

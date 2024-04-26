package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse;

import java.time.LocalDate;

public record BookingResponse2(Integer bookingId,
                               int customerId,
                               String carVin,
                               LocalDate startDate,
                               LocalDate endDate,
                               CustomerResponse customer,
                               CarResponse car) {
}

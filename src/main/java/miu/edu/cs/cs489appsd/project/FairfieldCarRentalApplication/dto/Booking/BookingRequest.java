package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record BookingRequest(
        int customerId,
        @NotBlank(message = "Car VIN is required") String carVin,
        LocalDate startDate,
        LocalDate endDate,
        Integer carId

) { }

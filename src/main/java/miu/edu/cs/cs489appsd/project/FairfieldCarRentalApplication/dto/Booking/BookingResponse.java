package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking;

import java.time.LocalDate;

public record BookingResponse(
        Integer bookingId,
        int customerId,
        String carVin,
        LocalDate startDate,
        LocalDate endDate
) { }


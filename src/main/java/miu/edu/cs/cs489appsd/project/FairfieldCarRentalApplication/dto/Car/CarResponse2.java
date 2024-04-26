package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.CarOwner;

public record CarResponse2(int carId,
                           String vin,
                           String model,
                           String make,
                           Integer mileage,
                           int year,
                           double rentalRate,
                           OwnerResponse ownerResponse,
                           BookingResponse bookingResponse) {
}

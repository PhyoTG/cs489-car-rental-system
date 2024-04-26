package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car;

public record CarResponse(
        int carId,
        String vin,
        String model,
        String make,
        Integer mileage,
        int year,
        double rentalRate
) { }


package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car;

import jakarta.validation.constraints.NotBlank;

public record CarRequest(
        @NotBlank(message = "VIN is required") String vin,
        @NotBlank(message = "Model is required") String model,
        @NotBlank(message = "Make is required") String make,
        int mileage,
        int year,
        double rentalRate,
        int ownerId
) { }

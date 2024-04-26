package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking;

public record CostResponse(Integer bookingId,
                           String carModel,
                           String carMake,
                           Integer year,
                           String customerName,
                           String contactPhone,
                           Double cost,
                           String ownerName
                           ) {
}

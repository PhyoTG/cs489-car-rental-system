package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer;

public record CustomerResponse(Integer customerId,
                              String firstName,
                              String lastName,
                              String contactPhone,
                              String email) {
}

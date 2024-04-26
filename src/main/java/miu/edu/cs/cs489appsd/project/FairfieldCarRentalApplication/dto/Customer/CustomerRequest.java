package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "First Name is required") String firstName,
        @NotBlank(message = "Last Name is required") String lastName,
        @NotBlank(message = "Contact Phone is required") String contactPhone,
        @NotBlank(message = "Email is required") String email
) { }

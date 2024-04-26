package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner;

import jakarta.validation.constraints.NotBlank;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Car;

import java.util.List;

public record OwnerRequest(
        @NotBlank(message = "First Name is required") String firstName,
        @NotBlank(message = "Last Name is required") String lastName,
        @NotBlank(message = "Contact Phone is required") String contactPhone,
        @NotBlank(message = "Email is required") String email,
        List<Integer> carList

) { }

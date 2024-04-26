package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner;

public record OwnerResponse(
        int ownerId,
        String firstName,
        String lastName,
        String contactPhone,
        String email
) { }

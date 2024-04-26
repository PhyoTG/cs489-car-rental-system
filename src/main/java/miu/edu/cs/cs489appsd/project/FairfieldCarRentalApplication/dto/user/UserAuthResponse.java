package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user;

public record UserAuthResponse(String jwtToken,
                               String firstName,
                               String lastName) {
}

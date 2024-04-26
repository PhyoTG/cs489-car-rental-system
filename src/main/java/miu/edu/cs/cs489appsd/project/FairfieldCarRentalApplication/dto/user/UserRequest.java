package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user;

import java.util.List;

public record UserRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        List<Integer> roleIds
) {
}

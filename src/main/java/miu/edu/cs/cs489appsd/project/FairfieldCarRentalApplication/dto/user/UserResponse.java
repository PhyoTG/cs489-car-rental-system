package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.role.RoleResponse;

import java.util.List;

public record UserResponse(Integer userId,
                           String username,
                           String password,
                           String firstName,
                           String lastName,
                           String email,
                           boolean accountNonExpired,
                           boolean accountNonLocked,
                           boolean credentialsNonExpired,
                           boolean enabled,
                           List<RoleResponse> roleResponses) {
}

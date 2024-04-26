package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Role;

public interface RoleService {
    Role addNewRole(Role role);

    Role getRoleById(Integer id);
}

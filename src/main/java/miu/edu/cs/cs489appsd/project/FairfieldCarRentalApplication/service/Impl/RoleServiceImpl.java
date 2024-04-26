package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Role;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.RoleRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id)
                .orElse(null);
    }
}

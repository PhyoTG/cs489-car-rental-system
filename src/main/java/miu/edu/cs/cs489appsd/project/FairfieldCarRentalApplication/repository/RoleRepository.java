package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);

}

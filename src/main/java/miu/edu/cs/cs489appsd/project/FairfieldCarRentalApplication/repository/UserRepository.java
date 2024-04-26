package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

}

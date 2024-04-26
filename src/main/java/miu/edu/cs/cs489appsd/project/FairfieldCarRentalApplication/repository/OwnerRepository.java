package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<CarOwner, Integer> {
    // Additional custom methods can be defined here if needed
}


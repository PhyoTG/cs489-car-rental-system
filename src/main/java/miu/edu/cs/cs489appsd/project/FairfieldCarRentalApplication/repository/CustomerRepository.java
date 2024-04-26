package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Additional custom methods can be defined here if needed
}


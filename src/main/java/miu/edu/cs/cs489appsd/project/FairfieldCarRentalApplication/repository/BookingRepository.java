package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Additional custom methods can be defined here if needed
}


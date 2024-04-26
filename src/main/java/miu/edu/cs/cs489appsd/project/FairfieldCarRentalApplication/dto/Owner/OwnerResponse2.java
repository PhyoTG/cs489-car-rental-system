package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Car;

import java.util.List;

public record OwnerResponse2(int ownerId,
                             String firstName,
                             String lastName,
                             String contactPhone,
                             String email,
                             List<CarResponse> carList
) {
}

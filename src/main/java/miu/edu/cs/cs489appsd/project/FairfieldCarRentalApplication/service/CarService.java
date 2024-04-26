package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CarNotFoundException;

import java.util.List;

public interface CarService {
    CarResponse createCar(CarRequest request);
    CarResponse2 getCar(Integer CarId) throws CarNotFoundException;
    CarResponse2 updateCar(Integer CarId, CarRequest request) throws CarNotFoundException ;
    void deleteCar(Integer CarId);
    List<CarResponse2> getAllCars();
}

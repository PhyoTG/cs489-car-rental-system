package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CarNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CustomerNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.CarService;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ffweb/api/v1/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CarResponse> createCustomer(@RequestBody CarRequest request) {
        CarResponse response = carService.createCar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarResponse2> getCaar(@PathVariable int carId) throws CarNotFoundException {
        CarResponse2 response = carService.getCar(carId);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{carId}")
    public ResponseEntity<CarResponse2> updateCar(@PathVariable int carId,
                                                           @RequestBody CarRequest request) throws CarNotFoundException {
        CarResponse2 response = carService.updateCar(carId, request);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable int carId) {
        carService.deleteCar(carId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CarResponse2>> getAllCars() {
        List<CarResponse2> responses = carService.getAllCars();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}


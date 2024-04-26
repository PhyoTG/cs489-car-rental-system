package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CarNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Car;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.CarOwner;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.CarRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.OwnerRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository CarRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CarServiceImpl(CarRepository CarRepository, OwnerRepository ownerRepository) {
        this.CarRepository = CarRepository;
        this.ownerRepository = ownerRepository;
    }
    @Override
    public CarResponse createCar(CarRequest request) {
        CarOwner owner = ownerRepository.getById(request.ownerId());
        var Car = CarRepository.save(
                new Car(null,
                        request.vin(),
                        request.model(),
                        request.make(),
                        request.mileage(),
                        request.year(),
                        request.rentalRate(),
                        owner));
        return new CarResponse(
                        Car.getCarId(),
                        Car.getVin(),
                        Car.getModel(),
                        Car.getMake(),
                        Car.getMileage(),
                        Car.getYear(),
                        Car.getRentalRate());
    }

    @Override
    public CarResponse2 getCar(Integer CarId) throws CarNotFoundException {
        Car cara = CarRepository.findById(CarId)
                .orElseThrow(() -> new CarNotFoundException(String.format("Error: Car with Id, %d, is not found",
                        CarId)));
        return cara!=null? new CarResponse2(
                cara.getCarId(),
                cara.getVin(),
                cara.getModel(),
                cara.getMake(),
                cara.getMileage(),
                cara.getYear(),
                cara.getRentalRate(),
                cara.getCarOwner()!= null?
                        new OwnerResponse(
                                cara.getCarOwner().getOwnerId(),
                                cara.getCarOwner().getFirstName(),
                                cara.getCarOwner().getLastName(),
                                cara.getCarOwner().getContactPhone(),
                                cara.getCarOwner().getEmail()
                        ):null,
                cara.getBooking()!=null?
                        new BookingResponse(
                                cara.getBooking().getBookingId(),
                                cara.getBooking().getCustomer().getCustomerId(),
                                cara.getVin(),
                                cara.getBooking().getStartDate(),
                                cara.getBooking().getEndDate()

                        ):null
        ):null;
    }

    @Override
    public CarResponse2 updateCar(Integer CarId, CarRequest request) throws CarNotFoundException{
        var Car = CarRepository.findById(CarId) .orElseThrow(() -> new CarNotFoundException(String.format("Error: Car with Id, %d, is not found",
                CarId)));
        if(Car != null ) {
            Car.setCarId(CarId);
            Car.setVin(request.vin());
            Car.setMake(request.make());
            Car.setModel(request.model());
            Car.setMileage(request.mileage());
            Car.setYear(request.year());
            Car.setRentalRate(request.rentalRate());
            CarRepository.save(Car);

            return new CarResponse2(
                    Car.getCarId(),
                    Car.getVin(),
                    Car.getModel(),
                    Car.getMake(),
                    Car.getMileage(),
                    Car.getYear(),
                    Car.getRentalRate(),
                    Car.getCarOwner()!= null?
                            new OwnerResponse(
                                    Car.getCarOwner().getOwnerId(),
                                    Car.getCarOwner().getFirstName(),
                                    Car.getCarOwner().getLastName(),
                                    Car.getCarOwner().getContactPhone(),
                                    Car.getCarOwner().getEmail()
                            ):null,
                    Car.getBooking()!=null?
                            new BookingResponse(
                                    Car.getBooking().getBookingId(),
                                    Car.getBooking().getCustomer().getCustomerId(),
                                    Car.getVin(),
                                    Car.getBooking().getStartDate(),
                                    Car.getBooking().getEndDate()

                            ):null);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCar(Integer CarId) {
        CarRepository.deleteById(CarId);
    }

    @Override
    public List<CarResponse2> getAllCars() {
        return CarRepository.findAll(Sort.by(Sort.Direction.ASC,"model"))
                .stream()
                .map(a -> new CarResponse2(
                        a.getCarId(),
                        a.getVin(),
                        a.getModel(),
                        a.getMake(),
                        a.getMileage(),
                        a.getYear(),
                        a.getRentalRate(),
                        a.getCarOwner()!= null?
                                new OwnerResponse(
                                        a.getCarOwner().getOwnerId(),
                                        a.getCarOwner().getFirstName(),
                                        a.getCarOwner().getLastName(),
                                        a.getCarOwner().getContactPhone(),
                                        a.getCarOwner().getEmail()
                                ):null,
                        a.getBooking()!=null?
                                new BookingResponse(
                                        a.getBooking().getBookingId(),
                                        a.getBooking().getCustomer().getCustomerId(),
                                        a.getVin(),
                                        a.getBooking().getStartDate(),
                                        a.getBooking().getEndDate()

                                ):null
                )).toList();
    }
}

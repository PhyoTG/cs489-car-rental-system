package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;


import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.BookingNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Booking;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Car;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Customer;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.BookingRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.CarRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.CustomerRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository BookingRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository BookingRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.BookingRepository = BookingRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        Customer customer = customerRepository.getById(request.customerId());
        Car car = carRepository.getById(request.carId());
        var Booking = BookingRepository.save(new Booking(null,request.startDate(),request.endDate(),customer,car));
        return new BookingResponse(Booking.getBookingId(),Booking.getCustomer().getCustomerId(),Booking.getCar().getVin(),Booking.getStartDate(),Booking.getEndDate());
    }

    @Override
    public BookingResponse2 getBooking(Integer BookingId) throws BookingNotFoundException {
        Booking a = BookingRepository.findById(BookingId)
                .orElseThrow(() -> new BookingNotFoundException(String.format("Error: Booking with Id, %d, is not found",
                        BookingId)));
        return a!=null? new BookingResponse2(
                a.getBookingId(),
                a.getCustomer().getCustomerId(),
                a.getCar().getVin(),
                a.getStartDate(),
                a.getEndDate(),
                a.getCustomer()!=null?
                        new CustomerResponse(
                                a.getCustomer().getCustomerId(),
                                a.getCustomer().getFirstName(),
                                a.getCustomer().getLastName(),
                                a.getCustomer().getContactPhone(),
                                a.getCustomer().getEmail()
                        ):null,
                a.getCar()!= null ?
                        new CarResponse(
                                a.getCar().getCarId(),
                                a.getCar().getVin(),
                                a.getCar().getModel(),
                                a.getCar().getMake(),
                                a.getCar().getMileage(),
                                a.getCar().getYear(),
                                a.getCar().getRentalRate()
                        ):null
        ):null;
    }

    @Override
    public BookingResponse2 updateBooking(Integer BookingId, BookingRequest request) throws BookingNotFoundException{
        var a = BookingRepository.findById(BookingId) .orElseThrow(() -> new BookingNotFoundException(String.format("Error: Booking with Id, %d, is not found",
                BookingId)));
        if(a != null ) {
            a.setBookingId(BookingId);
            a.setStartDate(request.startDate());
            a.setEndDate(request.endDate());
            BookingRepository.save(a);

            return new BookingResponse2(
                    a.getBookingId(),
                    a.getCustomer().getCustomerId(),
                    a.getCar().getVin(),
                    a.getStartDate(),
                    a.getEndDate(),
                    a.getCustomer()!=null?
                            new CustomerResponse(
                                    a.getCustomer().getCustomerId(),
                                    a.getCustomer().getFirstName(),
                                    a.getCustomer().getLastName(),
                                    a.getCustomer().getContactPhone(),
                                    a.getCustomer().getEmail()
                            ):null,
                    a.getCar()!= null ?
                            new CarResponse(
                                    a.getCar().getCarId(),
                                    a.getCar().getVin(),
                                    a.getCar().getModel(),
                                    a.getCar().getMake(),
                                    a.getCar().getMileage(),
                                    a.getCar().getYear(),
                                    a.getCar().getRentalRate()
                            ):null);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBooking(Integer BookingId) {
        BookingRepository.deleteById(BookingId);
    }

    @Override
    public List<BookingResponse2> getAllBookings() {
        return BookingRepository.findAll(Sort.by(Sort.Direction.ASC,"startDate"))
                .stream()
                .map(a -> new BookingResponse2(
                        a.getBookingId(),
                        a.getCustomer()!=null? a.getCustomer().getCustomerId():null,
                        a.getCar()!= null ? a.getCar().getVin(): null,
                        a.getStartDate(),
                        a.getEndDate(),
                        a.getCustomer()!=null?
                                new CustomerResponse(
                                        a.getCustomer().getCustomerId(),
                                        a.getCustomer().getFirstName(),
                                        a.getCustomer().getLastName(),
                                        a.getCustomer().getContactPhone(),
                                        a.getCustomer().getEmail()
                                ):null,
                        a.getCar()!= null ?
                                new CarResponse(
                                        a.getCar().getCarId(),
                                        a.getCar().getVin(),
                                        a.getCar().getModel(),
                                        a.getCar().getMake(),
                                        a.getCar().getMileage(),
                                        a.getCar().getYear(),
                                        a.getCar().getRentalRate()
                                ):null
                )).toList();
    }
}

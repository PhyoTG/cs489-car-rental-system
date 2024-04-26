package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Booking.BookingResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CustomerNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Customer;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.CustomerRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(new Customer(null,request.firstName(),request.lastName(),request.contactPhone(),request.email()));
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getContactPhone(),
                customer.getEmail());
    }

    @Override
    public CustomerResponse2 getCustomer(Integer customerId) throws CustomerNotFoundException {
        Customer a = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found",
                        customerId)));
        return a!=null? new CustomerResponse2(
                a.getCustomerId(),
                a.getFirstName(),
                a.getLastName(),
                a.getContactPhone(),
                a.getEmail(),
                a.getBooking()!= null?
                        new BookingResponse(
                                a.getBooking().getBookingId(),
                                a.getBooking().getCustomer().getCustomerId(),
                                a.getBooking().getCar().getVin(),
                                a.getBooking().getStartDate(),
                                a.getBooking().getEndDate()
                        ) : null
        ):null;
    }

    @Override
    public CustomerResponse2 updateCustomer(Integer customerId, CustomerRequest request) throws CustomerNotFoundException{
        var customer = customerRepository.findById(customerId) .orElseThrow(() -> new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found",
                customerId)));
        if(customer != null ) {
            customer.setCustomerId(customerId);
            customer.setFirstName(request.firstName());
            customer.setLastName(request.lastName());
            customer.setContactPhone(request.contactPhone());
            customer.setEmail(request.email());
            customerRepository.save(customer);

            return new CustomerResponse2(
                    customer.getCustomerId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getContactPhone(),
                    customer.getEmail(),
                    customer.getBooking()!= null?
                            new BookingResponse(
                                    customer.getBooking().getBookingId(),
                                    customer.getBooking().getCustomer().getCustomerId(),
                                    customer.getBooking().getCar().getVin(),
                                    customer.getBooking().getStartDate(),
                                    customer.getBooking().getEndDate()
                            ) : null
            );
        } else {
            return null;
        }
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerResponse2> getAllCustomers() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"))
                .stream()
                .map(a -> new CustomerResponse2(
                        a.getCustomerId(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getContactPhone(),
                        a.getEmail(),
                        a.getBooking()!= null?
                                new BookingResponse(
                                        a.getBooking().getBookingId(),
                                        a.getBooking().getCustomer().getCustomerId(),
                                        a.getBooking().getCar().getVin(),
                                        a.getBooking().getStartDate(),
                                        a.getBooking().getEndDate()
                                ) : null
                )).toList();
    }
}

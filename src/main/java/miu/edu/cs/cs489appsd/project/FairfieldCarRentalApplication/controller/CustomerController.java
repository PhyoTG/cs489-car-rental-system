package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CustomerNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ffweb/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create a new customer
    @PostMapping(value = "/register")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get a customer by ID
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerResponse2> getCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        CustomerResponse2 response = customerService.getCustomer(customerId);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a customer by ID
    @PutMapping(value = "/{customerId}")
    public ResponseEntity<CustomerResponse2> updateCustomer(@PathVariable int customerId,
                                                           @RequestBody CustomerRequest request) throws CustomerNotFoundException {
        CustomerResponse2 response = customerService.updateCustomer(customerId, request);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a customer by ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    // Get all customers
    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerResponse2>> getAllCustomers() {
        List<CustomerResponse2> responses = customerService.getAllCustomers();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}


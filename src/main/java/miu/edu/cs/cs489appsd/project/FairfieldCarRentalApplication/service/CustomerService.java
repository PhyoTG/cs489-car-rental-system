package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse2 getCustomer(Integer customerId) throws CustomerNotFoundException;
    CustomerResponse2 updateCustomer(Integer customerId, CustomerRequest request) throws CustomerNotFoundException ;
    void deleteCustomer(Integer customerId);
    List<CustomerResponse2> getAllCustomers();
}

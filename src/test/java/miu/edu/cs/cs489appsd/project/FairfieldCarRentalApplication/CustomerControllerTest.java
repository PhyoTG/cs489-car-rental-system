package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.CustomerNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Customer;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.CustomerRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp(){

    }

    @Test
    public void testGetCustomerById() throws CustomerNotFoundException {
        Customer expectedCustomer = new Customer(2, "Carey","NN", "111-222-3333", "carey@gmail.com");

        when(customerRepository.findById(2)).thenReturn(Optional.of(expectedCustomer));

        CustomerResponse2 actualResponse = customerService.getCustomer(2);

        assertEquals(expectedCustomer.getCustomerId(), actualResponse.customerId());
        assertEquals(expectedCustomer.getFirstName(), actualResponse.firstName());
        assertEquals(expectedCustomer.getLastName(), actualResponse.lastName());
        assertEquals(expectedCustomer.getContactPhone(), actualResponse.contactPhone());
        assertEquals(expectedCustomer.getEmail(), actualResponse.email());
    }
}

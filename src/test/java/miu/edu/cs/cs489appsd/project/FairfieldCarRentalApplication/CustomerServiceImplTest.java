package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller.CustomerController;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Customer.CustomerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp(){

    }

    @Test
    public void testGetAllCustomer(){
        List<CustomerResponse2> expectedCustomers = Arrays.asList(
                new CustomerResponse2(1, "Phyo", "GiGi","111-222-3333", "phyo@gmail.com",null),
                new CustomerResponse2(2, "Carey","NN", "111-222-3333", "carey@gmail.com",null)
        );

        when(customerService.getAllCustomers()).thenReturn(expectedCustomers);

        ResponseEntity<List<CustomerResponse2>> responseEntity = customerController.getAllCustomers();
        List<CustomerResponse2> actualCustomers = responseEntity.getBody();

        assertEquals(expectedCustomers.size(), actualCustomers.size());
        assertEquals(expectedCustomers.get(0).firstName(), actualCustomers.get(0).firstName());
        assertEquals(expectedCustomers.get(0).lastName(), actualCustomers.get(0).lastName()); // Corrected assertion
        assertEquals(expectedCustomers.get(0).contactPhone(), actualCustomers.get(0).contactPhone());
        assertEquals(expectedCustomers.get(0).email(), actualCustomers.get(0).email());
    }
}

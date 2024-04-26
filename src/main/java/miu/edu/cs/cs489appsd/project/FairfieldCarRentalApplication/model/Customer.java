package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String contactPhone;
    private String email;

    @OneToOne(mappedBy = "customer")
    private Booking booking;

    public Customer(Integer customerId, String firstName, String lastName, String contactPhone, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactPhone = contactPhone;
        this.email = email;
    }
}

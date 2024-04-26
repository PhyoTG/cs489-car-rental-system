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
@Table(name="carowners")
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerId;
    private String firstName;
    private String lastName;
    private String contactPhone;
    private String email;

    @OneToMany(mappedBy = "carOwner")
    private List<Car> carList;

    public CarOwner(Integer ownerId, String firstName, String lastName, String contactPhone, String email) {
        this.ownerId = ownerId;
        this.firstName =firstName;
        this.lastName = lastName;
        this.contactPhone = contactPhone;
        this.email = email;
    }
    // Constructors, getters, and setters


}

package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private String vin;
    private String model;
    private String make;
    private int mileage;
    private int year;
    private double rentalRate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "carOwner", unique = false, nullable = true)
    private CarOwner carOwner;

    @OneToOne(mappedBy = "car")
    private Booking booking;

    public Car(Integer carId,String vin,String model, String make, int mileage, int year, double rentalRate, CarOwner ownerId) {
        this.carId =carId;
        this.vin = vin;
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.rentalRate = rentalRate;
        this.carOwner = ownerId;
    }
}

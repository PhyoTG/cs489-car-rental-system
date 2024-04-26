package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer", unique = false, nullable = true)
    private Customer customer;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "car", unique = false, nullable = true)
    private Car car;

    public Booking(Integer bookingId, LocalDate startDate, LocalDate endDate) {
        this.bookingId =bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

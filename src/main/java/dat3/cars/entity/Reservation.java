package dat3.cars.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Lombok Start
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Lombok End
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Member member;

    @ManyToOne
    Car car;

    @CreationTimestamp
    LocalDateTime reservationDate;
    String rentalDate;

    public Reservation(Member member, Car car, String rentalDate) {
        this.member = member;
        this.car = car;
        this.rentalDate = rentalDate;
    }
}

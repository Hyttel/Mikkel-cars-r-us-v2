package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    int id;
    String username;
    int carId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime reservationDate;

    String rentalDate;

    public ReservationResponse(Reservation r) {
        this.id = r.getId();
        this.reservationDate = r.getReservationDate();
        this.rentalDate = r.getRentalDate();
        this.username = r.getMember().getUsername();
        this.carId = r.getCar().getId();
    }
}

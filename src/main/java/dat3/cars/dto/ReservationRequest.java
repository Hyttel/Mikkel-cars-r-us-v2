package dat3.cars.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

    String username;
    int carId;
    String date;

}

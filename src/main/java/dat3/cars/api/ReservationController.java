package dat3.cars.api;


import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    ReservationService reservationService;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationController(ReservationService reservationService, MemberRepository memberRepository, CarRepository carRepository){
        this.reservationService = reservationService;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @PostMapping("{username}/{carId}/{date}")
    public ReservationResponse makeReservation(@PathVariable("username") String username,
                                               @PathVariable("carId") int carId, @PathVariable("date")String date){
        reservationService.reserveCar(username,carId,date);
        Reservation reservation = reservationService.getReservation(reservationService.getReservationId());
        return new ReservationResponse(reservation);
    }

}

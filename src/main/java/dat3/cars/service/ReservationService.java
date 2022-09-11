package dat3.cars.service;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    private static int reservationId;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public void reserveCar(String username, int carId, String date) {

        Member member = memberRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));
        Car car = carRepository.findById(carId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "car not found"));
        boolean exists = reservationRepository.existsByCar_IdAndRentalDate(carId, date);

        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "car already reserved");
        } else {
            Reservation reservation = new Reservation(member, car, date);
            reservationRepository.save(reservation);
            reservationId = reservationRepository.save(reservation).getId();
        }
    }

    public Reservation getReservation(int id) {
        return reservationRepository.findById(id).orElseThrow();
    }

    public int getReservationId() {
        return reservationId;
    }
}

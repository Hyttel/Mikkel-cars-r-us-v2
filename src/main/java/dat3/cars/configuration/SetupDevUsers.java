package dat3.cars.configuration;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    CarRepository carRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository,
                         MemberRepository memberRepository,
                         CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        Member m1 = new Member("member1", passwordUsedByAll, "Henning@Henning.dk", "Henning", "Honning", "Honningvej 2", "Honningby", "1000");
        memberRepository.save(m1);

        Car car1 = Car.builder()
                .brand("Volvo")
                .model("V70")
                .pricePrDay(700)
                .bestDiscount(30.0)
                .build();

        Car car2 = Car.builder()
                .brand("Toyota")
                .model("Yaris")
                .pricePrDay(10)
                .bestDiscount(5)
                .build();

        carRepository.save(car1);
        carRepository.save(car2);

        Reservation res1 = new Reservation(m1, car1, "11,11,2022");
        Reservation res2 = new Reservation(m1, car1, "11,12,2022");
        m1.addReservation(res1);
        m1.addReservation(res2);
        reservationRepository.save(res1);
        reservationRepository.save(res2);

        setupUserWithRoleUsers();
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}

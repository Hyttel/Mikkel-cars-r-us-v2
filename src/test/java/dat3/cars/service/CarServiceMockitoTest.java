package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CarServiceMockitoTest {

    @Mock
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @BeforeEach
    public void setup() {
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(
                new Car(1, "1", "1", 1, 1),
                new Car(2, "2", "2", 2, 2)
        ));
        List<CarResponse> cars = carService.getCars();
        assertEquals(2, cars.size());
    }


    @Test
    void addCar() {
        Car c = new Car(1, "1", "1", 1, 1);
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(c);
        CarRequest request = new CarRequest(c);
        CarResponse found = carService.addCar(request);
        assertEquals(1, found.getId());
    }

    @Test
    void editCar() {
    }

    @Test
    void findCarByIdTest() {
        Car c = new Car(2, "2", "2", 2, 2);
        Mockito.when(carRepository.findById(2)).thenReturn(Optional.of(c));

        CarResponse response = carService.findCarById(2);
        assertEquals(2,response.getId());
    }

    @Test
    void findCarByIdThrowsTest()  {
        Mockito.when(carRepository.findById(2)).thenReturn(Optional.empty());
        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,()-> carService.findCarById(2));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }


    @Test
    void setRankingForUser() {
    }

    @Test
    void deleteByUsername() {
    }
}

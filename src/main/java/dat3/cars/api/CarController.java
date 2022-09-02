package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    public CarController(CarService carService) {
        this.carService = carService;
    }

    CarService carService;

    @PostMapping
    public CarResponse addMember(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body,id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }




}

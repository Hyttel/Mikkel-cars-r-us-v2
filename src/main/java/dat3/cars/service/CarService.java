package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponse addCar(CarRequest carRequest){
        //Later you should add error checks --> Missing arguments, email taken etc.

        if(carRepository.existsById(carRequest.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this ID already exist");
        }

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, false);
    }

    public void editCar(CarRequest body, int id){
        Car car = carRepository.findById(id).orElseThrow(
                ()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id already exist"));

        //if(!body.getId().equals(id)){
        if (body.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change id");
        }
        car.setId(body.getId());
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
    }

    public List<CarResponse> getCars() {
        List<Car> cars = carRepository.findAll();

        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car,false)).collect(Collectors.toList());

        return response;
    }

    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

    public void save(Car car){
        carRepository.save(car);
    }

    public CarResponse findCarById(@PathVariable int id) {
        Car found = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        return new CarResponse(found,false);
    }

}

package dat3.cars.dto;

import dat3.cars.entity.Car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

    int id;
    String brand;
    String model;
    double pricePrDay;
    double bestDiscount;
    LocalDateTime created;
    LocalDateTime edited;

    public static Car getCarEntity(CarRequest c){
        return new Car(c.getId(),c.getBrand(),c.getModel(), c.getPricePrDay(), c.getBestDiscount());
    }

    public CarRequest(Car c) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
        this.created = c.getCreated();
        this.edited = c.getEdited();
    }
}

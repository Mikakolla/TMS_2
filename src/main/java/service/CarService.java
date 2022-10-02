package service;

import entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarService {

    private List<Car> cars = new ArrayList(){{
        add(new Car(1,"Audi", "A5", 20000));
        add(new Car(2, "BMW", "3", 25000));
        add(new Car(3, "Jaguar", "F-Pace", 42000));
    }};

    public void deleteCar(int id) {

        List<Car> car = cars.stream().filter(car1 -> car1.getId() == id).collect(Collectors.toList());

        cars.removeAll(car);
    }
}

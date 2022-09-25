package entity;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private List<Car> cars = new ArrayList<>();

    public List<Car> getAll() {
        return cars;
    }

    public void createCar(int id, String brand){
        cars.add(new Car(id, brand));
    }

    public Car getCarById(int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst().get();
    }

    public void deleteCar(int id) {
        cars.remove(getCarById(id));
    }

}

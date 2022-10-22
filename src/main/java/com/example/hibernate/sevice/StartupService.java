package com.example.hibernate.sevice;

import com.example.hibernate.entity.Brand;
import com.example.hibernate.entity.Car;
import com.example.hibernate.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class StartupService {

    @Autowired
    private CarRepo carRepo;

    @PostConstruct
    public void init() throws InterruptedException {
        Car car1 = new Car("1", Brand.AUDI, new Date(), true);
        Car car2 = new Car("2", Brand.VW, new Date(), false);
        Car car3 = new Car("3", Brand.BMW, new Date(), true);
        Car car4 = new Car("4", Brand.VOLVO, new Date(), true);
        Car car5 = new Car("5", Brand.OPEL, new Date(), false);

        List<Car> cars = List.of(car1, car2, car3, car4, car5);
        carRepo.save(cars);

        Thread.sleep(10000);
        car1.setStockAvailability(false);
        carRepo.update(car1);

        carRepo.delete(car2);

        List<Car> allCars = carRepo.getAll();

        System.out.println("allCars = " + allCars);
    }
}

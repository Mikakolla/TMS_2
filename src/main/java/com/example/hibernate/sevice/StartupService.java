package com.example.hibernate.sevice;

import com.example.hibernate.dto.PersonDTO;
import com.example.hibernate.entity.Brand;
import com.example.hibernate.entity.Car;
import com.example.hibernate.entity.Person;
import com.example.hibernate.entity.Region;
import com.example.hibernate.repository.CarRepo;
import com.example.hibernate.repository.PersonRepo;
import com.example.hibernate.repository.RegionRepo;
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

    @Autowired
    private RegionRepo regionRepo;

    @Autowired
    private PersonRepo personRepo;

    @PostConstruct
    public void init() throws InterruptedException {

        Region region1 = new Region("Минск");
        Region region2 = new Region("Гродно");
        Region region3 = new Region("Витебск");
        Region region4 = new Region("Могилев");
        Region region5 = new Region("Гомель");

        List<Region> regions = List.of(region1, region2, region3, region4, region5);

        regionRepo.save(regions);

        Person person1 = new Person("Сергей");
        Person person2 = new Person("Влад");
        Person person3 = new Person("Ира");
        Person person4 = new Person("Алина");
        Person person5 = new Person("Женя");

        List<Person> people = List.of(person1, person2, person3, person4, person5);

        personRepo.save(people);

        Car car1 = new Car("1", Brand.AUDI, new Date(), true);
        Car car2 = new Car("2", Brand.VW, new Date(), false);
        Car car3 = new Car("3", Brand.BMW, new Date(), true);
        Car car4 = new Car("4", Brand.VOLVO, new Date(), true);
        Car car5 = new Car("5", Brand.OPEL, new Date(), false);

        car1.setRegion(region1);
        car2.setRegion(region2);
        car3.setRegion(region3);
        car4.setRegion(region4);
        car5.setRegion(region4);

        car1.setPerson(person1);
        car2.setPerson(person3);
        car3.setPerson(person3);
        car4.setPerson(person4);
        car5.setPerson(person5);

        List<Car> cars = List.of(car1, car2, car3, car4, car5);
        carRepo.save(cars);

        List<Car> allCars = carRepo.getAll();

        List<PersonDTO> all = personRepo.getAll();
    }
}

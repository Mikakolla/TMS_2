package com.example.hibernate.repository;

import com.example.hibernate.dto.CarDTO;
import com.example.hibernate.dto.PersonDTO;
import com.example.hibernate.entity.Car;
import com.example.hibernate.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonRepo {

    @Autowired
    private SessionFactory factory;

    public void save(List<Person> people) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Person person : people) {
            session.save(person);
        }
        transaction.commit();
        session.close();
    }

    public List<PersonDTO> getAll() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> rootEntry = query.from(Person.class);
        CriteriaQuery<Person> all = query.select(rootEntry);

        TypedQuery<Person> allQuery = session.createQuery(all);
        List<Person> people = allQuery.getResultList();
        List<PersonDTO> peopleDTO = new ArrayList<>();

        for (Person person : people) {
            List<CarDTO> carsDTO = new ArrayList<>();

            for (Car car : person.getCars()) {

                CarDTO carDTO = new CarDTO(car.getNumber(), car.getBrand(), car.getRegion());
                carsDTO.add(carDTO);
            }

            PersonDTO personDTO = new PersonDTO(person.getName(), carsDTO);
            peopleDTO.add(personDTO);
        }

        transaction.commit();
        session.close();

        return peopleDTO;
    }
}

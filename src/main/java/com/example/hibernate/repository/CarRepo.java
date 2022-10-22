package com.example.hibernate.repository;

import com.example.hibernate.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class CarRepo {

    @Autowired
    private SessionFactory factory;

    public void save(List<Car> cars) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Car car : cars) {
            session.save(car);
        }
        transaction.commit();
        session.close();
    }

    public void update(Car car) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(car);
        transaction.commit();
        session.close();
    }

    public void delete(Car car) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public List<Car> getAll() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        Root<Car> rootEntry = query.from(Car.class);
        CriteriaQuery<Car> all = query.select(rootEntry);

        TypedQuery<Car> allQuery = session.createQuery(all);
        List<Car> cars = allQuery.getResultList();
        transaction.commit();
        session.close();

        return cars;
    }
}

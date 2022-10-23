package com.example.hibernate.repository;

import com.example.hibernate.entity.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionRepo {

    @Autowired
    private SessionFactory factory;

    public void save(List<Region> regions) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Region region : regions) {
            session.save(region);
        }
        transaction.commit();
        session.close();
    }
}

package com.example.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Temporal(TemporalType.DATE)
    private Date dateCreateCar;

    @Temporal(TemporalType.TIME)
    @CreationTimestamp
    private Date dateCreate;

    @Temporal(TemporalType.TIME)
    @UpdateTimestamp
    private Date dateUpdate;

    private boolean stockAvailability;

    @Version
    private int version;

    public Car(String number, Brand brand, Date dateCreateCar, boolean stockAvailability) {
        this.number = number;
        this.brand = brand;
        this.dateCreateCar = dateCreateCar;
        this.stockAvailability = stockAvailability;
    }
}

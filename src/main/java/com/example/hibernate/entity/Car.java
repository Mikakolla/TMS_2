package com.example.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name="regionId")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;

    public Car(String number, Brand brand, Date dateCreateCar, boolean stockAvailability) {
        this.number = number;
        this.brand = brand;
        this.dateCreateCar = dateCreateCar;
        this.stockAvailability = stockAvailability;
    }
}

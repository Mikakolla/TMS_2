package com.example.hibernate.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    private String name;

    @OneToMany(mappedBy="person")
    private List<Car> cars;

    public Person(String name) {
        this.name = name;
    }
}

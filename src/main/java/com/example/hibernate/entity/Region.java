package com.example.hibernate.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;

    private String name;

    @OneToMany(mappedBy="region")
    private List<Car> cars;

    public Region(String name) {
        this.name = name;
    }
}

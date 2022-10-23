package com.example.hibernate.dto;

import com.example.hibernate.entity.Brand;
import com.example.hibernate.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
public class CarDTO {

    private String number;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="regionId")
    private Region region;
}

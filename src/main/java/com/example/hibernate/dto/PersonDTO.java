package com.example.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {

    private String name;
    private List<CarDTO> cars;
}

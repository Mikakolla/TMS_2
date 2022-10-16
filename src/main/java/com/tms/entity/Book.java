package com.tms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    private String author;
}

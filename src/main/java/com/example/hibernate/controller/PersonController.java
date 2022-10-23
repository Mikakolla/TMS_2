package com.example.hibernate.controller;

import com.example.hibernate.dto.CarDTO;
import com.example.hibernate.dto.PersonDTO;
import com.example.hibernate.sevice.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getAllPeople(Model model) {
        List<PersonDTO> allPerson = personService.getAllPerson();

        model.addAttribute("allPerson", allPerson);
        return "persons";
    }
}

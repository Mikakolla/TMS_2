package com.example.hibernate.sevice;

import com.example.hibernate.dto.PersonDTO;
import com.example.hibernate.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public List<PersonDTO> getAllPerson() {
        return personRepo.getAll();
    }
}

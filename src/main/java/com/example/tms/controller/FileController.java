package com.example.tms.controller;

import com.example.tms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Bean
    public void addToFile() throws IOException {
        fileService.addNote();
    }
}

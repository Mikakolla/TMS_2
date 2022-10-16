package com.example.tms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    @Value("${file.path}")
    private String fileName;

    public void addNote() throws IOException {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        int countReq;

        String readLine = br.readLine();

        if (readLine != null){
            countReq = Integer.parseInt(readLine) + 1;
        } else {
            countReq = 1;
        }

        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.append(Integer.toString((countReq)));

        writer.close();
        br.close();
    }
}

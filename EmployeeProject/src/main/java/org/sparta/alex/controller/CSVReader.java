package org.sparta.alex.controller;

import org.sparta.alex.model.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static EmployeeRepository readValues(String path){
        EmployeeRepository employees = new EmployeeRepository();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                employees.addToListFromLine(line);
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;

    }
}

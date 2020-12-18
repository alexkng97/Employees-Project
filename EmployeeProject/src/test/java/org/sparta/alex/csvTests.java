package org.sparta.alex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.model.EmployeeRepository;

public class csvTests {


    @Test
    public void csvReadAndAddEmployee(){
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/testCSVs/csvTestSuccess.csv");
        Assertions.assertEquals(5,employeeRepository.getEmployeeList().size());
    }


}

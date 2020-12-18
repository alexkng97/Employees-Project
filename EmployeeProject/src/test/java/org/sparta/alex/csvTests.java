package org.sparta.alex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.model.EmployeeRepository;

public class csvTests {
    static Logger logger = LogManager.getLogger(csvTests.class);


    @Test
    public void csvReadAndAddEmployee(){
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/testCSVs/csvTestSuccess.csv");
        Assertions.assertEquals(5,employeeRepository.getEmployeeList().size());
        logger.info("CSV read and added employees successfully");
    }

    @Test
    public void duplicateIDInCSV(){
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/testCSVs/empIdDuplicate.csv");
        employeeRepository.filterEmployees();
        Assertions.assertEquals(2, employeeRepository.getInvalidList().size());
        Assertions.assertEquals(3, employeeRepository.getEmployeeList().size());
        logger.info("Duplicate IDs test successful");
    }

    @Test
    public void duplicateEmailInCSV(){
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/testCSVs/emailDuplicate.csv");
        employeeRepository.filterEmployees();
        Assertions.assertEquals(2, employeeRepository.getInvalidList().size());
        Assertions.assertEquals(3, employeeRepository.getEmployeeList().size());
        logger.info("Duplicate emails test successful");
    }

    @Test
    public void invalidDates(){
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/testCSVs/invalidDates.csv");
       // employeeRepository.filterEmployees();
        Assertions.assertEquals(1, employeeRepository.getInvalidList().size());
        Assertions.assertEquals(4, employeeRepository.getEmployeeList().size());
        logger.info("Invalid dates test successful");
    }





}

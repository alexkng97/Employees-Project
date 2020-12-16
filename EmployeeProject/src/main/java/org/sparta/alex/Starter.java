package org.sparta.alex;

import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.model.EmployeeDAO;
import org.sparta.alex.model.EmployeeRepository;

public class Starter {


    public static void main(String[] args) {

        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/EmployeeRecords.csv");
        employeeRepository.filterEmployeeId();

        String url = "jdbc:mysql://localhost:3306/tester";
        EmployeeDAO.connectToDB(url);
        
        EmployeeDAO.insertListOfEmployees(employeeRepository.getEmployeeList());
      // EmployeeDAO.queryDB("SELECT * FROM tester.employees");

    }
}

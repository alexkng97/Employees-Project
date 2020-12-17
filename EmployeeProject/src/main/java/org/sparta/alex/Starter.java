package org.sparta.alex;

import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.model.*;

import java.util.ArrayList;
import java.util.List;

public class Starter {


    public static void main(String[] args) {

        System.out.println("Reading in .csv file...");
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/EmployeeRecords.csv");
        System.out.println("Filtering .csv file...");
        long startFilter = System.currentTimeMillis();
        employeeRepository.filterEmployees();
        System.out.println("Time taken to filter out invalid data: " + (System.currentTimeMillis() - startFilter) + " ms");
        System.out.println("Number of valid employee records: " + employeeRepository.getEmployeeList().size());


        String url = "jdbc:mysql://localhost:3306/tester";
        long startConnection = System.currentTimeMillis();
        EmployeeDAO.connectToDB(url);
        System.out.println("Time taken to connect to database: " + (System.currentTimeMillis() - startConnection) + " ms");

//        long start = System.currentTimeMillis();
//        EmployeeDAO.insertListOfEmployees(employeeRepository.getEmployeeList());
//        System.out.println("Time taken to populate database: " + (System.currentTimeMillis() - start) +" ms" );
       // EmployeeDAO.queryDB("SELECT * FROM tester.employees");



        System.out.println("---------THREADS-----------");
        ThreadManager threadManager = new ThreadManager(employeeRepository.getEmployeeList(), 15);
        threadManager.splitEmployeeList();
        threadManager.initAndRunThreads();

    }
}

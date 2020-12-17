package org.sparta.alex;

import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.model.*;

public class Starter {


    public static void main(String[] args) {

        System.out.println("Reading in .csv file...");
        long startReadFile = System.currentTimeMillis();
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/EmployeeRecordsLarge.csv");
        System.out.println("Time taken to read in data: " + (System.currentTimeMillis() - startReadFile) + " ms");

        System.out.println("Filtering .csv file...");
        long startFilter = System.currentTimeMillis();
        employeeRepository.filterEmployees();
        System.out.println("Time taken to filter out invalid data: " + (System.currentTimeMillis() - startFilter) + " ms");
        System.out.println("Number of valid employee records: " + employeeRepository.getEmployeeList().size());


        String url = "jdbc:mysql://localhost:3306/tester";
        long startConnection = System.currentTimeMillis();
        EmployeeDAO.connectToDB(url);
        System.out.println("Time taken to connect to database: " + (System.currentTimeMillis() - startConnection) + " ms");


        System.out.println("---------THREADS-----------");
        ThreadManager threadManager = new ThreadManager(employeeRepository.getEmployeeList(), 20);
        threadManager.splitEmployeeList();
        threadManager.initAndRunThreads();

    }
}

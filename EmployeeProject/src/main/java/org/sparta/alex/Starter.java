package org.sparta.alex;

import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.model.EmployeeDAO;
import org.sparta.alex.model.EmployeeDTO;
import org.sparta.alex.model.EmployeeRepository;
import org.sparta.alex.model.EmployeeThread;

import java.util.ArrayList;
import java.util.List;

public class Starter {


    public static void main(String[] args) {

        System.out.println("Reading in .csv file...");
        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/EmployeeRecords.csv");
        System.out.println("Filtering .csv...");
        employeeRepository.filterEmployeeId();
        System.out.println("Number of valid employee records: " + employeeRepository.getEmployeeList().size());


        String url = "jdbc:mysql://localhost:3306/tester";
        long startConnection = System.currentTimeMillis();
        EmployeeDAO.connectToDB(url);
        System.out.println("Time taken to connect to database: " + (System.currentTimeMillis() - startConnection) + " ms");

        //EmployeeDAO.insertListOfEmployees(employeeRepository.getEmployeeList());
       // EmployeeDAO.queryDB("SELECT * FROM tester.employees");

        int midpoint = employeeRepository.getEmployeeList().size() / 2;
        ArrayList<EmployeeDTO> firstList = new ArrayList<>(employeeRepository.getEmployeeList().subList(0, midpoint));
        ArrayList<EmployeeDTO> secondList = new ArrayList<>(employeeRepository.getEmployeeList().subList(midpoint,
                employeeRepository.getEmployeeList().size()));


        Thread thread1 = new Thread(new EmployeeThread(firstList));
        Thread thread2 = new Thread(new EmployeeThread(secondList));

        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        System.out.println("Time taken to populate database: " + (System.currentTimeMillis() - start) +" ms" );


    }
}

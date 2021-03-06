package org.sparta.alex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sparta.alex.controller.CSVReader;
import org.sparta.alex.controller.ThreadManager;
import org.sparta.alex.model.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Starter {
    static Logger logger = LogManager.getLogger(Starter.class);
    private static final Properties properties = new Properties();

    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/login.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        createProperties();
        //URL FOR DATABASE:
        String url = properties.getProperty("url");

        logger.info("Program started");
        long startTotal = System.currentTimeMillis();
        System.out.println("Reading in .csv file...");
        long startReadFile = System.currentTimeMillis();

        EmployeeRepository employeeRepository = CSVReader.readValues("src/main/resources/EmployeeRecordsLarge.csv");
        System.out.println("Time taken to read in data: " + (System.currentTimeMillis() - startReadFile) + " ms");

        logger.info("Time taken to read in data from csv: " + (System.currentTimeMillis() - startReadFile) + " ms");

        System.out.println("Filtering .csv file...");
        long startFilter = System.currentTimeMillis();
        employeeRepository.filterEmployees();

        System.out.println("\tNumber of valid employee records: " + employeeRepository.getEmployeeList().size());
        System.out.println("\tTime taken to filter out invalid data: " + (System.currentTimeMillis() - startFilter) + " ms");

        System.out.println("Writing invalid records to InvalidEmployeeRecords.csv...");
        employeeRepository.writeInvalidToFile("src/main/resources/InvalidEmployeeRecords.csv");


        long startConnection = System.currentTimeMillis();
        EmployeeDAO.connectToDB(url);
        System.out.println("Time taken to connect to database: " + (System.currentTimeMillis() - startConnection) + " ms");
        logger.info("Time taken to connect to database: " + (System.currentTimeMillis() - startConnection) + " ms");


        System.out.println("---------THREADS-----------");
        int threadSize = 20;
        ThreadManager threadManager = new ThreadManager(employeeRepository.getEmployeeList(), threadSize);
        threadManager.splitEmployeeList();
        threadManager.initAndRunThreads();

        System.out.println("Time for total process: " + (System.currentTimeMillis() - startTotal) + " ms");
        logger.info("Time for total process: " + (System.currentTimeMillis() - startTotal) + " ms with " + threadSize + " threads.");

    }
}

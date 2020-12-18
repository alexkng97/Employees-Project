package org.sparta.alex.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class EmployeeThread implements Runnable{
    private ArrayList<EmployeeDTO> employeeList;
    private Connection connection;
    private static final Properties properties = new Properties();

    static Logger logger = LogManager.getLogger(EmployeeThread.class);


    public EmployeeThread(ArrayList<EmployeeDTO> employeeList){
        this.employeeList = employeeList;

    }

    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/login.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        createProperties();
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        System.out.println(Thread.currentThread().getName() + " started");
        long start = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tester?rewriteBatchedStatements=true",username,password);
            start = System.currentTimeMillis();
            EmployeeDAO.insertInBatches(employeeList,connection);
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " complete! Took " + (System.currentTimeMillis() - start) + " ms");
        logger.info(Thread.currentThread().getName() + " complete! Took " + (System.currentTimeMillis() - start) + " ms");
    }
}

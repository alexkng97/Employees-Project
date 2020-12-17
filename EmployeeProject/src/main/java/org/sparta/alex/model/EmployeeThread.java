package org.sparta.alex.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeThread implements Runnable{
    private ArrayList<EmployeeDTO> employeeList;
    private Connection connection;

    public EmployeeThread(ArrayList<EmployeeDTO> employeeList){
        this.employeeList = employeeList;

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running");
        long start = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tester?rewriteBatchedStatements=true","root","spartaglobal");
            start = System.currentTimeMillis();
            EmployeeDAO.insertInBatches(employeeList,connection);
            connection.close();
            System.out.println(Thread.currentThread().getName() + " complete");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " took " + (System.currentTimeMillis() - start) + " ms");
    }
}

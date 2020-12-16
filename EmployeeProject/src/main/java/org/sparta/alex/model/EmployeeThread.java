package org.sparta.alex.model;

import java.util.ArrayList;

public class EmployeeThread implements Runnable{
    private ArrayList<EmployeeDTO> employeeList;

    public EmployeeThread(ArrayList<EmployeeDTO> employeeList){
        this.employeeList = employeeList;

    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running");
        EmployeeDAO.insertListOfEmployees(employeeList);
    }
}

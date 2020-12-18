package org.sparta.alex.controller;

import org.sparta.alex.model.EmployeeDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter {

    public static void writeListToFile(ArrayList<EmployeeDTO> employeeList, String url){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(url));
            writer.write("Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary\n");

            for(EmployeeDTO employee : employeeList){
                StringBuilder sb = new StringBuilder();

                sb.append(employee.getEmp_ID()).append(",").append(employee.getNamePrefix()).append(",")
                        .append(employee.getFirstName()).append(",").append(employee.getMiddleInitial()).append(",")
                        .append(employee.getLastName()).append(",").append(employee.getGender()).append(",")
                        .append(employee.getEmail()).append(",").append(employee.getDob()).append(",")
                        .append(employee.getDateOfJoining()).append(",").append(employee.getSalary()).append("\n");

                writer.write(sb.toString());
            }

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

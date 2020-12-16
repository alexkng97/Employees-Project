package org.sparta.alex.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EmployeeDTO {
    private String emp_ID;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dob;
    private LocalDate dateOfJoining;
    private int salary;


    public EmployeeDTO(String emp_ID, String namePrefix, String firstName, String middleInitial, String lastName,
                       String gender, String email, String dob, String dateOfJoining, String salary) {
        this.emp_ID = emp_ID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        setDob(dob);
        setDateOfJoining(dateOfJoining);
        setSalary(salary);
    }

    public EmployeeDTO() {

    }

    public String getEmp_ID() {
        return emp_ID;
    }

    public void setEmp_ID(String emp_ID) {
        this.emp_ID = emp_ID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
        ;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = LocalDate.parse(dateOfJoining, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = Integer.valueOf(salary);
    }


    public String toString() {
        return "Employee " + emp_ID + ", " + namePrefix + " " + firstName + " " + middleInitial + " " + lastName;
    }

}

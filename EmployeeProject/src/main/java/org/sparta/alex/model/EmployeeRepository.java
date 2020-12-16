package org.sparta.alex.model;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeRepository {
    private ArrayList<EmployeeDTO> employeeList;
    private ArrayList<EmployeeDTO> invalidList;

    private Map<String,Integer> employeeIds;

    public EmployeeRepository() {
        this.employeeList = new ArrayList<>();
        this.invalidList = new ArrayList<>();
        this.employeeIds = new HashMap<>();
    }

    public ArrayList<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    public ArrayList<EmployeeDTO> getInvalidList() {
        return invalidList;
    }

    public void addToListFromLine(String line) {
        String[] split = line.split(",");
        EmployeeDTO currentEmployee = new EmployeeDTO();

        currentEmployee.setEmp_ID(split[0]);

        if(!employeeIds.containsKey(split[0])){
            employeeIds.put(split[0],1);
        }else{
            int value = employeeIds.get(split[0])+ 1;
            employeeIds.put(split[0], value);
        }
        currentEmployee.setNamePrefix(split[1]);
        currentEmployee.setFirstName(split[2]);
        currentEmployee.setMiddleInitial(split[3]);
        currentEmployee.setLastName(split[4]);
        currentEmployee.setGender(split[5]);
        currentEmployee.setEmail(split[6]);
        currentEmployee.setDob(split[7]);
        currentEmployee.setDateOfJoining(split[8]);
        currentEmployee.setSalary(split[9]);

        if(currentEmployee.getDateOfJoining().compareTo(currentEmployee.getDob()) < 0){
            invalidList.add(currentEmployee);
        }


        employeeList.add(currentEmployee);


    }

    public void filterEmployeeId(){
        Map<String, Integer> duplicates =
                employeeIds.entrySet()
                .stream()
                .filter(e-> e.getValue() > 1)
                .collect(Collectors.toMap(e-> e.getKey(), e -> e.getValue()));


        for(EmployeeDTO employee : this.employeeList){
            if(duplicates.containsKey(employee.getEmp_ID())){
                invalidList.add(employee);
            }
        }


        employeeList.removeAll(invalidList);

        System.out.println("Amount of Duplicates found: " +invalidList.size());
    }


}

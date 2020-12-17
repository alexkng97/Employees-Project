package org.sparta.alex.model;

import java.util.ArrayList;

public class ThreadManager {
    private Thread[] employeeThreads;
    private int size;
    private ArrayList<ArrayList<EmployeeDTO>> splitList;
    private ArrayList<EmployeeDTO> employeeList;

    public ThreadManager(ArrayList<EmployeeDTO> employeeList, int size){
        this.size = size;
        this.employeeList = employeeList;
       // employeeThreads = new Thread[size];
        splitList = new ArrayList<>(size);

    }
    public void splitEmployeeList(){

        int startIndexOfSplit = 0;
        int interval = employeeList.size() / size;

        for(int i = 0; i < size ; i ++){
            ArrayList<EmployeeDTO> splitToAdd =new ArrayList<>(employeeList.subList(startIndexOfSplit, startIndexOfSplit + interval));
            splitList.add(splitToAdd);
            startIndexOfSplit += interval;
        }

        int leftOverRecords = employeeList.size() - (interval * size);

        if(leftOverRecords > 0){
            for(int i = employeeList.size() - leftOverRecords; i < employeeList.size();i++) {
                splitList.get(0).add(employeeList.get(i));
            }
        }

    }

    public void initAndRunThreads(){

        try {
            employeeThreads = new Thread[size];

            long start = System.currentTimeMillis();
            for(int i = 0 ; i < employeeThreads.length; i++){
                employeeThreads[i] = new Thread( new EmployeeThread(splitList.get(i)));
                employeeThreads[i].start();
            }

            for(int i = 0 ; i < employeeThreads.length; i++){
                employeeThreads[i].join();
            }

            System.out.println("Time taken for threads to run: " + (System.currentTimeMillis() - start) + " ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }







}

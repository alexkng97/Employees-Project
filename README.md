# Employees Project

To run program, users should run Starter.java.

**IMPORTANT NOTE:** User should provide a **login.properties** file in _src/main/resources_, which should include their url, username and password for their database.

Users must have pre-existing empty database with an employees table with correct columns of:
  
* emp_id, name_prefix, first_name, middle_initial, last_name, gender, email, dob, date_joined, salary 

Insert operations and queries have been implemented with the structure of database called "tester" and schema called "employees"

Java program that reads in a CSV file of employee records and filters out based on:

* Duplicate employee IDs
* Duplicate email addresses
* Invalid dates: if the employee DOB is past their Date of Joining

Program then writes the invalid employee records to CSV file for user to check: _"InvalidEmployeeRecords.csv"_

Program populates database with valid employee records using threads (current number of threads is set to 20).

Program outputs status of process in both the command line and log file, with the associated timings.

package org.sparta.alex.model;


import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class EmployeeDAO {
    private static Connection connection;
    private static final Properties properties = new Properties();


    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/login.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void connectToDB(String url){
        createProperties();
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try{
            connection = DriverManager.getConnection(url,username,password);

        }catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("Connected to DB");

    }

    public static void queryDB(String query){
        StringBuilder sb = new StringBuilder();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                sb.append(resultSet.getString(1));
                sb.append(resultSet.getString(2));
                sb.append(resultSet.getString(3));
                sb.append(resultSet.getString(4));
                sb.append(resultSet.getString(5));
                sb.append(resultSet.getString(6));
                sb.append(resultSet.getString(7));
                sb.append(resultSet.getDate(8));
                sb.append(resultSet.getDate(9));
                sb.append(resultSet.getInt(10));
                sb.append("\n");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(sb.toString());

    }


    public static void insertData(String emp_ID, String namePrefix, String firstName, String middleInitial, String lastName,
                                  String gender, String email, LocalDate dob, LocalDate dateOfJoining, int salary, Connection thisConnection){
        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
                    "insert INTO `tester`.`employees` (`emp_id`,`name_prefix`,`first_name`, `middle_initial`, " +
                            "`last_name`, `gender`,`email`, `dob`, `date_joined`, `salary`) " +
                            "values (?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,emp_ID);
            preparedStatement.setString(2,namePrefix);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, middleInitial);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, email);
            preparedStatement.setDate(8, Date.valueOf(dob));
            preparedStatement.setDate(9, Date.valueOf(dateOfJoining));
            preparedStatement.setInt(10, salary);

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static void insertSingleEmployee(EmployeeDTO employee,Connection thisConnection){
        insertData(employee.getEmp_ID(), employee.getNamePrefix(), employee.getFirstName(), employee.getMiddleInitial(),
                employee.getLastName(), employee.getGender(), employee.getEmail(),employee.getDob(), employee.getDateOfJoining(),
                employee.getSalary(), thisConnection);


    }

    public static void insertListOfEmployees(ArrayList<EmployeeDTO> employeeList, Connection thisConnection){
        //System.out.println("Populating Database with Employee List...");
        for(EmployeeDTO employee: employeeList){
            insertSingleEmployee(employee, thisConnection);
        }


    }


    public static void insertInBatches(ArrayList<EmployeeDTO> employeeList, Connection thisConnection)  {

        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
                    "insert INTO `tester`.`employees` (`emp_id`,`name_prefix`,`first_name`, `middle_initial`, " +
                            "`last_name`, `gender`,`email`, `dob`, `date_joined`, `salary`) " +
                            "values (?,?,?,?,?,?,?,?,?,?)");

            for(EmployeeDTO employee : employeeList){
                preparedStatement.setString(1,employee.getEmp_ID());
                preparedStatement.setString(2,employee.getNamePrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4, employee.getMiddleInitial());
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6, employee.getGender());
                preparedStatement.setString(7, employee.getEmail());
                preparedStatement.setDate(8, Date.valueOf(employee.getDob()));
                preparedStatement.setDate(9, Date.valueOf(employee.getDateOfJoining()));
                preparedStatement.setInt(10, employee.getSalary());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




}

package org.sparta.alex.model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getString(6));
                System.out.println(resultSet.getString(7));
                System.out.println(resultSet.getDate(8));
                System.out.println(resultSet.getDate(9));
                System.out.println(resultSet.getInt(10));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public static void insertData(int id, String namePrefix, String email){
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("insert INTO `tester`.`user` (`id`,`name`,`email`) values (?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,namePrefix);
            preparedStatement.setString(3, email);

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }




}

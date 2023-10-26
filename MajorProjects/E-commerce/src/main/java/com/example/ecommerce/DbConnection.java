package com.example.ecommerce;
import java.sql.*;
public class DbConnection {

    private final String dbUrl = "jdbc:mysql://localhost:3306/ecommerce";

    private  final String userName = "root";

    private final String password = "1234";

    private Statement getStatement(){
        try{
            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            return connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query){
        try {
          Statement statement = getStatement();
          return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int updateDatabase(String query){
        try {
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        DbConnection conn = new DbConnection();
        ResultSet rs = conn.getQueryTable("Select * From customer");

        if(rs != null){
            System.out.println("Connection Successful");
        }
        else{
            System.out.println("Connection Unsuccessful");
        }
    }
}
//The DriverManager class acts as an interface between users and drivers

//getConnection() is used to establish the connection with the specified url

//The createStatement() method of Connection interface is used to create statement
// The object of statement is responsible to execute queries with the database

//The executeQuery() method of Statement interface is used to execute queries to the database
//This method returns the object of ResultSet that can be used to get all the records of a table

//After the execution the ResultSet object contains the retrieved data from database after the given
// query is executed
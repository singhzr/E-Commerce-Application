package com.example.ecommerce;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    public static Customer customerLogin(String userName,String password){

        String query = "SELECT * FROM customer WHERE email= '"+userName+ "'and password= '"+password+"'";
        DbConnection connection = new DbConnection();
        try {
            ResultSet rs = connection.getQueryTable(query);

            if(rs!=null && rs.next()){   // The ResultSet is not null,
                                        // rs.next() checks there is at least one row of data
                                       //  You can retrieve and process this data here
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile")
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static int signUp(String name, String email, String add, String pass, String number) {
        String s = "Insert into customer(name,email,address,password, mobile) " +
                   "values('"+name+"','"+email+"','"+add+"','"+pass+ "','"+ number +"')";
        try{
            DbConnection dbConn = new DbConnection();
            return dbConn.updateDatabase(s);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        Login login = new Login();
        Customer customer = login.customerLogin("angad@gmail.com", "abc123");
        System.out.println("WELCOME" +customer.getName());
    }
}
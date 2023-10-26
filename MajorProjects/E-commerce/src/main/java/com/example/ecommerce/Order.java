package com.example.ecommerce;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;

public class Order {

    public static boolean placeOrder(Customer customer, Product product){
        String groupOrderId = "SELECT max(group_order_id)+1 id from orders";
        DbConnection dbConn = new DbConnection();
        try {
            ResultSet rs = dbConn.getQueryTable(groupOrderId);
            if(rs.next()) {
                String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id)" +
                        " VALUES (" + rs.getInt("id") + "," + customer.getId() + "," + product.getId() + ")";
                return dbConn.updateDatabase(placeOrder) != 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static int placeMultipleOrder(Customer customer, ObservableList<Product> productList){
        String groupOrderId = "SELECT max(group_order_id)+1 id from orders";
        DbConnection dbConn = new DbConnection();
        try {
            ResultSet rs = dbConn.getQueryTable(groupOrderId);
            int count = 0;
            if(rs.next()) {
                for (Product product : productList) {
                    String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id) VALUES " +
                                         "(" + rs.getInt("id") + "," + customer.getId() + "," + product.getId() + ")";
                    count += dbConn.updateDatabase(placeOrder);
                }
                return count;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
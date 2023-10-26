package com.example.ecommerce;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {

    private int id;
    private String name;
    private int price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public static ObservableList<Product> getAllProducts(){
        String selectAllProducts = "SELECT id, name, price FROM product";

        return fetchProductData(selectAllProducts);
    }
    public static ObservableList<Product> fetchProductData(String query){
        DbConnection dbConn = new DbConnection();

        ObservableList<Product> result = FXCollections.observableArrayList();
        try {
            ResultSet rs = dbConn.getQueryTable(query);

            while (rs.next()){
                //Taking out from ResultSet
                result.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
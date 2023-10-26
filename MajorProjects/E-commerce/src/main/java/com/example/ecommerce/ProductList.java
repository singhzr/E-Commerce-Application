package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProductList {
    public static TableView<Product> productTable;

    public static VBox createTable(ObservableList<Product> data) {
        //Quantity column
        TableColumn id = new TableColumn("Id");
        id.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn name = new TableColumn<>("Name");
        name.setMinWidth(200);
        name.setCellValueFactory(new PropertyValueFactory("name"));

        //Price column
        TableColumn price = new TableColumn<>("Price");
        price.setMinWidth(100);
        price.setCellValueFactory(new PropertyValueFactory("price"));

        productTable = new TableView<>();
        productTable.getColumns().addAll(id, name, price);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(productTable);

        return vbox;
    }
    public VBox getAllProducts(){
        ObservableList<Product> data = Product.getAllProducts();
        return createTable(data);
    }
    public Product getSelectedProduct() {
        // getting selected item
        return productTable.getSelectionModel().getSelectedItem();
    }

    public  VBox getProductsInCart(ObservableList<Product> data){
        return createTable(data);
    }
}










//TableView<Product> it takes objects type  and extract and place each entity of object in separate column

//setCellValueFactory() method is called on each TableColumn to specify how the data should be extracted from the Product objects
//for each column. It uses the PropertyValueFactory to link the column with the corresponding property of the Product class.

//setCellValueFactory() It will extract similar type(id or name or price or any other) and display them in same column

//ObservableList provides facility to listen to events and modify accordingly while ArrayList can't listen to events and modify
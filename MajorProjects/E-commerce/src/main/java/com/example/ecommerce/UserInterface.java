package com.example.ecommerce;

import com.sun.javafx.menu.MenuItemBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


public class UserInterface {
    GridPane loginPage;
    Pane bodyPane;
    Button signupButton = new Button("Sign Up");
    HBox headerBar;
    VBox placedOrders;
    HBox footerBar;
    Button signInButton;
    VBox body;
    Button homeButton;
    Label messageLabel;
    Customer loggedInCustomer;
    Button signOutButton;
    ProductList productList = new ProductList();
    VBox productPage;
    Label welcomeLabel;
    TextField userName;
    PasswordField password;
    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();
    Button placeOrderButton = new Button("Place Order");
    Button orders = new Button("Orders");

    public BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);
        root.setTop(headerBar);
        root.setBottom(footerBar);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = productList.getAllProducts();
        body.getChildren().add(productPage);

        return root;
    }
    public UserInterface(){
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }
    public void createLoginPage(){

        Text userNameText = new Text("Username");
        Text passwordText = new Text("Password");

        userName = new TextField();
        userName.setPromptText("Type your username here");
        password = new PasswordField();

        password.setPromptText("Type your password here");
        messageLabel = new Label("Hi");
        Button loginButton = new Button("Login");

        loginPage = new GridPane();

        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(15);
        loginPage.setVgap(15);
        loginPage.add(userNameText, 0, 0);
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(messageLabel,0,2);
        loginPage.add(loginButton,1,2);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                footerBar.setVisible(true);
                loggedInCustomer = login.customerLogin(name, pass);

                if(loggedInCustomer != null){
                    messageLabel.setText("Welcome : " +loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome : " +loggedInCustomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                    headerBar.getChildren().add(signOutButton);
                }
                else{
                    messageLabel.setText("Login Failed");
                }
            }
        });
    }

    public void createHeaderBar() {
        homeButton = new Button();
        Image image = new Image("C:\\Users\\yousu\\Desktop\\MajorProjects\\E-commerce\\src\\img.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(60);
        homeButton.setGraphic(imageView);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Here");
        searchBar.setPrefWidth(280);
        Button searchButton = new Button("Search");
        signInButton = new Button("Sign In");
        signOutButton = new Button("Sign Out");
        welcomeLabel = new Label();
        headerBar = new HBox();

        Button cartButton = new Button("Cart");

        headerBar.setPadding(new Insets(10));
        headerBar.setSpacing(10);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(homeButton, signInButton, searchBar, searchButton, cartButton, orders);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear(); //remove everything
                body.getChildren().add(loginPage); //put login page
                headerBar.getChildren().remove(signInButton);
                footerBar.setVisible(true);

            }
        });
        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                headerBar.getChildren().add(signInButton);
                headerBar.getChildren().remove(signOutButton);
                headerBar.getChildren().remove(welcomeLabel);
                messageLabel.setText("Login");
                userName.clear();
                password.clear();
                itemsInCart.clear();
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage = productList.getProductsInCart(itemsInCart);
                prodPage.setSpacing(10);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (itemsInCart == null) {
                    showDialog("Please add some product in the Cart to Place Order");
                    return;
                }

                if (loggedInCustomer == null) {
                    showDialog("Please Login first to Place Order");
                    return;
                }

                int count = Order.placeMultipleOrder(loggedInCustomer, itemsInCart);
                if (count != 0) {
                    showDialog("Order for " + count + " products Placed Successfully");
                    placedOrders = productList.getProductsInCart(itemsInCart);
                } else {
                    showDialog("Order Failed");
                }

            }
        });
        orders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                if (placedOrders == null) {
                    showDialog("There are no Orders");
                    return;
                }
                body.getChildren().add(placedOrders);
            }
        });
    }
    private GridPane signUpPage(){
        Label userLabel = new Label("User Name");
        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        Label passLabel = new Label ("Password");
        Label confirmPassword = new Label ("Confirm Password");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        PasswordField confPassword = new PasswordField();
        confPassword.setPromptText("Confirm Password");
        Label userEmail = new Label("E-mail");
        TextField email = new TextField();
        email.setPromptText("Enter e-mail id");
        Label mobile = new Label("Phone");
        TextField mobileNum = new TextField();
        mobileNum.setPromptText("Enter Phone no.");
        Label userAddress = new Label ("Address");
        TextField address = new TextField();
        address.setPromptText("Enter your address");

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = password.getText();
                String confirmPassword= confPassword.getText();
                String eml = email.getText();
                String add = address.getText();
                String mono = mobileNum.getText();
                if(pass.equals(confirmPassword) && SignUp.validateEmail(eml)){
                    Login login = new Login();

                    login.signUp(user,eml,add,pass,mono);
                    showDialog("Signup Successful!! \n Please Login");
                }
                else{
                    showDialog("Sign Up Failed \n please try again");
                }
                body.getChildren().clear();
            }
        });


        GridPane signUpPane = new GridPane();
        signUpPane.setTranslateY(30); //moving the control
        signUpPane.setTranslateX(250);
        signUpPane.setVgap(10);
        signUpPane.setHgap(10);
        signUpPane.add(userLabel, 0, 0);
        signUpPane.add(userName, 1, 0);
        signUpPane.add(passLabel, 0, 1);
        signUpPane.add(password, 1, 1);
        signUpPane.add(confirmPassword , 0, 2);
        signUpPane.add(confPassword, 1, 2);
        signUpPane.add(userEmail , 0, 3);
        signUpPane.add(email, 1, 3);
        signUpPane.add(mobile , 0, 4);
        signUpPane.add(mobileNum, 1, 4);
        signUpPane.add(userAddress , 0, 5);
        signUpPane.add(address, 1, 5);
        signUpPane.add(signupButton,0, 6);

        return signUpPane;

    }
    Button createAccount = new Button("Create Account");
    public void createFooterBar() {

        Button addToCartButton = new Button("Add to Cart");
        footerBar = new HBox();
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(addToCartButton, createAccount);


        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(loggedInCustomer == null){
                    showDialog("Please Login first to Place Order");
                    return;
                }
                if(product == null){
                    showDialog("Please select a Product first to add it to cart");
                    return;
                }
                itemsInCart.add(product);
                showDialog("Selected item has been added in the Cart");
            }
        });

        createAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signUpPage());
                body.setAlignment(Pos.CENTER);
            }
        });
    }
    private void showDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}

//line107 Image image = new Image("C:\\Users\\yousu\\Desktop\\MajorProjects\\E-commerce\\src\\img.png");
//// loginPage.setStyle(" -fx-background-color: grey;");
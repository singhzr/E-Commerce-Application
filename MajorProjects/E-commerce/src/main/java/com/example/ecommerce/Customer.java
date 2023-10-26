package com.example.ecommerce;

public class Customer {
    int id;
    String name, email, mobile;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Customer(int id, String name, String email, String string) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}
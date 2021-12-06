package com.example.NotCursedWork.models;

import javax.persistence.*;

@Entity
public class Order1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double Price;
    private  String List_of_products;
    private boolean Status;
    private Long client_id, register_id;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public boolean isStatus() {
        return Status;
    }

    public Order1(String list_of_products) {
        List_of_products = list_of_products;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getList_of_products() {
        return List_of_products;
    }

    public void setList_of_products(String list_of_products) {
        List_of_products = list_of_products;
    }

    public Order1() {
    }

    public Order1(String list_of_products, Long client_id, Long register_id) {
        List_of_products = list_of_products;
        this.client_id = client_id;
        this.register_id = register_id;
    }

    public Long getRegister_id() {
        return register_id;
    }

    public void setRegister_id(Long register_id) {
        this.register_id = register_id;
    }

    public Order1(Long id, double price, String list_of_products, boolean status) {
        this.id = id;
        Price = price;
        List_of_products = list_of_products;
        Status = status;
    }
}
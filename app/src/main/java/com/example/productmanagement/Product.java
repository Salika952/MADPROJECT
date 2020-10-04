package com.example.productmanagement;

public class Product {

    String id;
    String name;
    int qty;
    int price;

    String description;
    String type;


    public Product() {
    }


    public Product(String id, String name, int qty, int price, String description, String type) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.description = description;
        this.type = type;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}

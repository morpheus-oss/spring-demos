package com.epam.demo.grocery.grocery.pojo;

import com.epam.demo.grocery.grocery.models.ProductEntity;

public class Product {

    private final int id;
    private final String name;
    private final int category;
    private final int quantity;
    private final double price;

    Product(ProductEntity entity)   {
        this.id = entity.getId();
        this.name = entity.getName();
        this.category = entity.getCategory();
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

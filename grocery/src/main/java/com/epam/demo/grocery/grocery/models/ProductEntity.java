package com.epam.demo.grocery.grocery.models;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class ProductEntity {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column (name = "name")
    String name;

    @Column (name = "category")
    int category;

    @Column (name = "quantity")
    int quantity;

    @Column (name = "price")
    double price;

    public ProductEntity(int id, String name, int category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
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

package com.onebox.core;

import java.math.BigInteger;


public class Product {
    private final BigInteger id;
    private final String description;
    private int quantity;

    public Product(String description, int quantity){
        this.description = description;
        this.quantity = quantity;
        this.id = IdGenerator.generate();
    }

    public BigInteger getProductID() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public int addQuantity(int quantity){
        this.quantity += quantity;
        return this.quantity;
    }
}

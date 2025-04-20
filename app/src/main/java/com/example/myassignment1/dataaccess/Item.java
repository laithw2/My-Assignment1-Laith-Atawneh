package com.example.myassignment1.dataaccess;

public class Item {
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int imageResId;

    public Item( String name, String category, double price, int quantity, int imageResId) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageResId = imageResId;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }


    public String getCategory() {
        return category;
    }


    public double getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public String toString() {
        return name + " - "+" ₪"+price;
    }
}

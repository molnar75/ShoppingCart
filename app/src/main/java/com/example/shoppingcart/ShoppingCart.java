package com.example.shoppingcart;

public class ShoppingCart {
    private String name;
    private int percentage;
    private boolean isItDone;
    private int id;

    public ShoppingCart(int id, String name, int percentage, boolean isItDone) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.isItDone = isItDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isItDone() {
        return isItDone;
    }

    public void setItDone(boolean itDone) {
        isItDone = itDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

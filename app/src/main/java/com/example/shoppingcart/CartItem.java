package com.example.shoppingcart;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String cartName;
    private String name;
    private int quantity;
    private boolean isItDone;

    public CartItem(String cartName, String name, int quantity, boolean isItDone) {
        this.cartName = cartName;
        this.name = name;
        this.quantity = quantity;
        this.isItDone = isItDone;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isItDone() {
        return isItDone;
    }

    public void setItDone(boolean itDone) {
        isItDone = itDone;
    }
}

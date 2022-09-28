package com.apps.storeapp.Models;

public class CartItemObject {
    private int id = 0;
    private String name = "";
    private String details = "";
    private int price = 0;
    private boolean has_toppings = false;
    private boolean has_cream = false;
    private int temp = 0;
    private int qty = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHas_toppings() {
        return has_toppings;
    }

    public void setHas_toppings(boolean has_toppings) {
        this.has_toppings = has_toppings;
    }

    public boolean isHas_cream() {
        return has_cream;
    }

    public void setHas_cream(boolean has_cream) {
        this.has_cream = has_cream;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

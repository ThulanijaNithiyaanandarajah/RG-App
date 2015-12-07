package com.example.user.rgapp;

/**
 * Created by fmfuser on 10/9/2015.
 */
public class Product {

    private String unique_id;
    private String item_name;
    private String item_price;
    private String item_discount;

    public Product() {
    }

    public Product(String unique_id, String item_name, String item_discount, String item_price) {
        this.unique_id = unique_id;
        this.item_name = item_name;
        this.item_discount = item_discount;
        this.item_price = item_price;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_discount() {
        return item_discount;
    }

    public void setItem_discount(String item_discount) {
        this.item_discount = item_discount;
    }
}


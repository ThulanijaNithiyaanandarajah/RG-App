package com.example.user.sortresult;

/**
 * Created by USER on 12/3/2015.
 */
public class Product {

    public String item_delivery_option;
    public String item_payment_option;
    public String item_brand;
    public String unique_id;
    public String item_name;
    public String item_price;
    public String item_discount;

    public Product(String item_name,String item_price,String item_discount,String unique_id) {

        this.item_name=item_name;
        this.item_price=item_price;
        this.unique_id=unique_id;
        this.item_discount=item_discount;
    }

    public String getItem_delivery_option() {
        return item_delivery_option;
    }

    public String getItem_brand() {
        return item_brand;
    }

    public void setItem_brand(String item_brand) {
        this.item_brand = item_brand;
    }

    public String getItem_payment_option() {

        return item_payment_option;
    }

    public void setItem_payment_option(String item_payment_option) {
        this.item_payment_option = item_payment_option;
    }

    public void setItem_delivery_option(String item_delivery_option) {
        this.item_delivery_option = item_delivery_option;
    }

    public Product(String item_name,String item_brand,String item_payment_option,String item_price,String item_delivery_option)
    {
        this.item_name=item_name;
        this.item_brand=item_brand;
        this.item_payment_option=item_payment_option;
        this.item_price=item_price;
        this.item_delivery_option=item_delivery_option;


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

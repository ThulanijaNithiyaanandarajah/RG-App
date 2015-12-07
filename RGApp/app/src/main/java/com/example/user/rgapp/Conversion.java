package com.example.user.rgapp;

import java.text.DecimalFormat;

/**
 * Created by fmfuser on 11/12/2015.
 */
public class Conversion {

    public Conversion(){

    }

    public static String getPriceInRupees(double price){
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        if(price==0){
            return "Rs. 0.00";
        }
        else{
            return "Rs. "+formatter.format(price);
        }
    }

    public static String getRealPriceInRupees(double price, double discount){

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        if(price==0){
            return "Rs. 0.00";
        }
        else{
            return "Rs. "+formatter.format(price - (price*(discount/100)));
        }
    }
}

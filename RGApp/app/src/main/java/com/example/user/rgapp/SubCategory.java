package com.example.user.rgapp;

import android.graphics.Bitmap;

/**
 * Created by USER on 11/30/2015.
 */
public class SubCategory {

    public String sub_name;
    public String sub_link;
    public int sub_type;
    public String image_url;
    public String image_alt;
    public Bitmap image = null;

    public SubCategory(String sub_name, String sub_link) {
        this.sub_name = sub_name;
        this.sub_link = sub_link;
        this.sub_type = sub_type;
        this.image_url = image_url;
        this.image_alt = image_alt;
        this.image = null;

    }

}

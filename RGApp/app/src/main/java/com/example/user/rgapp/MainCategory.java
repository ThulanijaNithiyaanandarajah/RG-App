package com.example.user.rgapp;

import java.util.ArrayList;

/**
 * Created by USER on 11/30/2015.
 */
public class MainCategory {

    public String category_title;
    public String category_image;
    public ArrayList<String> sub_list = new ArrayList<String>();

    public MainCategory(String category_title) {

        this.category_title = category_title;

    }

    public String toString(){
        return category_title;
    }

}

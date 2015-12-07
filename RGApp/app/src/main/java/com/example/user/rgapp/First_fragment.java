package com.example.user.rgapp;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Admin on 11/30/2015.
 */
public class First_fragment extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView= inflater.inflate(R.layout.first_layout,container,false);
       // return super.onCreateView(inflater, container, savedInstanceState);
        return myView;

    }
}
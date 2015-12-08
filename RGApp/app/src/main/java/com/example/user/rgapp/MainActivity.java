package com.example.user.rgapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class MainActivity extends Activity{

    Button button;
    TextView textView9;
    RequestQueue requestQueue;
    //String url = "http://cblunt.github.io/blog-android-volley/response.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ViewPager viewPager = (ViewPager) findViewById(R.id.slide);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        final EditText search_text=(EditText)findViewById(R.id.editText);
        Button mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(),gridActivity.class);
                        String text=search_text.getText().toString();
                        //  String search="search_text";
                        intent.putExtra("search_text",text);
                        startActivity(intent);
                        // mTextView.setText(text);
                    }
                });

        textView9 = (TextView) findViewById(R.id.textView9);
      //  tv = (TextView) findViewById(R.id.textView);

        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);
                startActivity(intent);
            }
        });



    }
}

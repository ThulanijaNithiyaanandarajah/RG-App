package com.example.user.rgapp;

/*import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ///////
        ///
    }

}*/


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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class MainActivity extends FragmentActivity {




    Button button;
  //  TextView tv;
    RequestQueue requestQueue;

    //String url = "http://cblunt.github.io/blog-android-volley/response.json";


    static final int NUM_ITEMS = 6;
    ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    ViewPager viewPager;
    public static final String[] IMAGE_NAME = {"leaderboard_kellyfelder", "leaderboard_laptop_carnival", "leaderboard_tv", "ntb", "sampath", "sampath_emi",};

    @Override
    protected void onCreate( Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_home_page);
    //    imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager());
    //    viewPager = (ViewPager) findViewById(R.id.slide);
     //   viewPager.setAdapter(imageFragmentPagerAdapter);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.slide);
        viewPager.setAdapter(imageFragmentPagerAdapter);






        button = (Button) findViewById(R.id.button);
        //  tv = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);
                startActivity(intent);
            }
        });

    }



    public static class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
        public ImageFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return SwipeFragment.newInstance(position);
        }
    }

    public static class SwipeFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View swipeView = inflater.inflate(R.layout.activity_main, container, false);
            ImageView imageView = (ImageView) swipeView.findViewById(R.id.imageView2);
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");
            String imageFileName = IMAGE_NAME[position];
            int imgResId = getResources().getIdentifier(imageFileName, "drawable", "com.example.user.rgapp");
            imageView.setImageResource(imgResId);
            return swipeView;
        }

        static SwipeFragment newInstance(int position) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            swipeFragment.setArguments(bundle);
            return swipeFragment;
        }
    }





}

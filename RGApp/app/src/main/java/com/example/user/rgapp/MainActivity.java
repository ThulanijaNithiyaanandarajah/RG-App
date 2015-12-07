package com.example.user.rgapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button;
    TextView tv;
    RequestQueue requestQueue;
    //String url = "http://cblunt.github.io/blog-android-volley/response.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Image Viewer
        ViewPager viewPager = (ViewPager) findViewById(R.id.slide);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);


        // VIEW ALL BUTTON
        button = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);
                startActivity(intent);
            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(int position) {


        Fragment myFragment = null;
        switch (position){
            case 0 :
                myFragment = new First_fragment();
                break;
            case 1 :
                myFragment = new Second_fragment();
                break;
            case 2 :
                myFragment = new Third_fragment();
                break;

        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, myFragment).commit();

        // Handle navigation view item clicks here.
        MenuItem item = null;
        int id = item.getItemId();
        /*
        if (id == R.id.nav_elec) {
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

            /*
            item=(MenuItem)findViewById(id);
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(getApplicationContext(), First_fragment.class);
                    intent.putExtra("sub_name", "Electronics");
                    intent.putExtra("sub_link", "electronics");
                    startActivity(intent);
                    return false;
                }
            });
            */
/*
        } else if (id == R.id.nav_FashionNlife) {

            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
            /*
            Intent intent = new Intent(getApplicationContext(),Second_fragment.class);
            intent.putExtra("sub_name","Fashion");
            intent.putExtra("sub_link","mens-fashion");
            startActivity(intent);

        } else if (id == R.id.nav_homeNeeds) {
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
            /*
            Intent intent = new Intent(getApplicationContext(),Third_fragment.class);
            intent.putExtra("sub_name","Home Needs");
            intent.putExtra("sub_link","home-and-living");
            startActivity(intent);

        }

*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Electronics:

                intent = new Intent(getApplicationContext(), List_View.class);
                intent.putExtra("sub_name","Electronics");
                intent.putExtra("sub_link","electronics");
                startActivity(intent);
                return true;
            case R.id.Fashion_and_Lifestyle:
                intent = new Intent(getApplicationContext(), List_View.class);
                intent.putExtra("sub_name","Fashion");
                intent.putExtra("sub_link","mens-fashion");
                startActivity(intent);
                return true;
            case R.id.Home_Needs:
                intent = new Intent(getApplicationContext(), List_View.class);
                intent.putExtra("sub_name","Home Needs");
                intent.putExtra("sub_link","home-and-living");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


        // if(if item== R.id.Electronics)

        //Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT).show();


    }


    }
}

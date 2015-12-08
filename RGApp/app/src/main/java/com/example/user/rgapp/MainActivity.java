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


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listview;
    TrendingSliderAdapter adapter;
    ViewPager viewPager;
    ArrayList<Product> listItem;

    Button button;
    Button cart;
    //TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        cart = (Button) findViewById(R.id.shoppingct);
        //tv = (TextView) findViewById(R.id.textView);

        //listItem = new ArrayList<product>();
        //new TrendAsyncTask().execute("http://www.retailgenius.com/cms/xml/trending_items.xml");

        viewPager = (ViewPager)findViewById(R.id.slide);

        //adapter = new TrendingSliderAdapter(this, listItem);
        //viewPager.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartView.class);
                startActivity(intent);
            }
        });

    }

    /*public class TrendAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            XMLParser parser = new XMLParser();

            try {
                String xml = parser.getXmlFromUrl(params[0]); // getting XML
                Document doc = parser.getDomElement(xml); // getting DOM element

                NodeList nl = doc.getElementsByTagName("row");
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);

                    NodeList cl = e.getChildNodes();

                    String unique_id = ((Element)cl.item(0)).getAttribute("unique_id");
                    String item_name = ((Element)cl.item(1)).getAttribute("item_name");
                    String item_price = ((Element)cl.item(2)).getAttribute("item_price");
                    String item_discount = ((Element)cl.item(3)).getAttribute("item_discount");
                        Product product = new Product(unique_id, item_name, item_price, item_discount);
                        listItem.add(product);

                    System.out.print(listItem);
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
        }
    }*/
    /*static final int NUM_ITEMS = 6;
    ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    ViewPager viewPager;
    public static final String[] IMAGE_NAME = {"leaderboard_kellyfelder", "leaderboard_laptop_carnival", "leaderboard_tv", "ntb", "sampath", "sampath_emi",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.slide);
        viewPager.setAdapter(imageFragmentPagerAdapter);
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
    }*/
}
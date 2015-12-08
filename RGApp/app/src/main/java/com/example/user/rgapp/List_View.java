package com.example.user.rgapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 12/3/2015.
 */
public class List_View extends Activity {

    private String sub_link;
    private String sub_name;
    private ArrayList<Product> productList = new ArrayList<>();
   private View listView;
    JSONArray contacts = null;
  //  ArrayList<HashMap<String, String>> contactList;
  //  private listAdapter listAdapter;
    private LinearLayout itemListContainer;
    Product product;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.list_view);
       // final TextView text=(TextView)findViewById(R.id.textView);
        listView=(View)findViewById(R.id.list);
       final TextView mtextView = (TextView) findViewById(R.id.textView);
  //      itemListContainer = (LinearLayout)findViewById(R.id.item_list_view);
     //   product=new Product();


        String url = "https://www.retailgenius.com/rg_api/" +"/get-items-for-sub-category.php";
        sub_name = getIntent().getExtras().getString("sub_name");

        sub_link = getIntent().getExtras().getString("sub_link");
        Toast.makeText(getApplicationContext(), sub_link.toString(), Toast.LENGTH_LONG).show();
        CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONObject sendResult = new JSONObject((response.toString()));
                   // mtextView.setText(response.toString());
                    productList =   getListFromJsonObject(sendResult);
                    ListView listview = (ListView) findViewById(R.id.list);
                    ItemListAdapter adapter = new ItemListAdapter(getApplicationContext(), R.layout.activity_view, productList);
                    listview.setAdapter(adapter);

                   /* productList =   getItemObjectListFromJSON(sendResult);
                    ListView listview;

                    listview = (ListView) findViewById(R.id.list);
                    listAdapter adapter = new listAdapter(getApplicationContext(), R.layout.list_view, productList);
                    listview.setAdapter(adapter);
                    //Toast.makeText(getApplicationContext(),listAdapter.toString(), Toast.LENGTH_LONG).show(); */
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                        assert sub_link != null;
                if (sub_link.contains("/products/brands/")) {
                    params.put("sub_link_brand", sub_link.replace("/products/brands/", ""));
                }
                else if (sub_link.contains("/products/")){
                    params.put("sub_link", sub_link.replace("/products/", ""));
                }
                else{
                    params.put("sub_link", sub_link);
                }
                return params;
            }

        };


        //jsObjRequest.setShouldCache(false);
        MyVolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);



    }
    private ArrayList<Product> getItemObjectListFromJSON(JSONObject jsonObject) throws JSONException {
        ArrayList<Product> list = new ArrayList<>();
        JSONArray dataArray = jsonObject.getJSONArray("data");
        for (int i =0; i<dataArray.length();i++){
            String item_name = dataArray.getJSONObject(i).getString("item_name");
            String item_price = dataArray.getJSONObject(i).getString("item_price");
            String unique_id = dataArray.getJSONObject(i).getString("unique_id");
            String item_discount = dataArray.getJSONObject(i).getString("item_discount");
            list.add(new Product(unique_id,item_name,item_price,item_discount));
        }
        return list;
    }

    public static ArrayList<Product> getListFromJsonObject(JSONObject jObject) throws JSONException {
        ArrayList<Product> return_list = new ArrayList<Product>();
        JSONArray dataArray =  jObject.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++)
        {
            String item_name=dataArray.getJSONObject(i).getString("item_name");
            String item_price = dataArray.getJSONObject(i).getString("item_price");
            String unique_id = dataArray.getJSONObject(i).getString("unique_id");
            String item_discount = dataArray.getJSONObject(i).getString("item_discount");
            return_list.add(new Product(item_name,item_price,item_discount,unique_id));

        }

        return return_list;


    }



}

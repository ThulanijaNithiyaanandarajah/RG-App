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
    ArrayList<HashMap<String, String>> contactList;
    private listAdapter listAdapter;
    private LinearLayout itemListContainer;
    Product product;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        final TextView text=(TextView)findViewById(R.id.textView);
        listView=(View)findViewById(R.id.list);
        itemListContainer = (LinearLayout)findViewById(R.id.item_list_view);
        product=new Product();


        String url = "https://www.retailgenius.com/rg_api/get-items-for-sub-category.php";
        sub_name = getIntent().getExtras().getString("sub_name");

        sub_link = getIntent().getExtras().getString("sub_link");
        Toast.makeText(getApplicationContext(), sub_link.toString(), Toast.LENGTH_LONG).show();
        CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONObject sendResult = new JSONObject((response.toString()));
                    productList =   getItemObjectListFromJSON(sendResult);
                    ListView listview;

                    listview = (ListView) findViewById(R.id.list);
                    listAdapter adapter = new listAdapter(getApplicationContext(), R.layout.list_view, productList);
                    listview.setAdapter(adapter);




                    //productList = getItemObjectListFromJSON(response);
                   // contacts = response.getJSONArray("data");


                  //  Log.d("Post param", "dsdsdsdsd");

                  //  Log.d("Post param", sub_link);
                   // Log.d("Response", response.toString());
                   // Log.d("Posted Param", response.getString("post_param"));
                 //  productList = getItemObjectListFromJSON(response);
                  // listAdapter=new listAdapter(List_View.this,R.layout.list_view,productList);

                  //  text.setText(listAdapter.toString());
                  //  Toast.makeText(getApplicationContext(),listAdapter.toString(), Toast.LENGTH_LONG).show();
                   // mView.setAdapter((ListAdapter) productList);
                 //  Log.d("Post param", "dsdsdsdsd");
                  //  mView.setAdapter(listAdapter);
                  //  itemListContainer.addView(mView);
                   // setFilterRadioTexts();


                   // text.setText(contacts.toString());
                /*

                   for (int i=0;i<contacts.length();i++ ) {
                       JSONObject c = contacts.getJSONObject(i);


                       product.setItem_discount(c.getString("item_discount"));
                       product.setItem_price(c.getString("item_price"));
                       product.setUnique_id(c.getString("unique_id"));
                       product.setItem_name(c.getString("item_name"));
                       productList.add(product);
                       Toast.makeText(getApplicationContext(), c.toString(), Toast.LENGTH_LONG).show();


                   }
                   */
                   // Toast.makeText(getApplicationContext(), productList.toString(), Toast.LENGTH_LONG).show();

                       //text.setText(item_discount+"+++"+item_price+"+++"+unique_id+"+++"+item_name);
                      // text.setText("");
                      // Toast.makeText(getApplicationContext(), item_discount+"+++"+item_price+"+++"+unique_id+"+++"+item_name, Toast.LENGTH_LONG).show();
                        /*
                       // tmp hashmap for single contact
                       HashMap<String, String> contact = new HashMap<String, String>();

                       // adding each child node to HashMap key => value
                       contact.put("item_discount", item_discount);
                       contact.put("item_price", item_price);
                       contact.put("unique_id", unique_id);
                       contact.put("item_name", item_name);

                       // adding contact to contact list
                       contactList.add(contact);


                  //    text.setText(productList.get(i).item_name);
                  //      Toast.makeText(getApplicationContext(), productList.get(i).item_name, Toast.LENGTH_LONG).show();
                  */



                  /*  ListAdapter adapter = new SimpleAdapter(
                            List_View.this, contactList,
                            R.layout.list_view, new String[] {"item_name", "item_price",
                            "unique_id","item_discount" }, new int[] { R.id.item_name,
                            R.id.item_price, R.id.unique_id,R.id.item_discount });

                    setListAdapter(adapter);
                    */
                   // mView = (GridView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_view, null);
                  //  Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

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




}

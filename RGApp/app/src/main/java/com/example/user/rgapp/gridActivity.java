package com.example.user.rgapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
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

public class gridActivity extends AppCompatActivity {


        GridView gridView;
       public String search_txt;
      ArrayList<Product> productList = new ArrayList<>();
        static final String[] numbers = new String[] {
                "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        private int viewmode=0;
        TextView mTextView;
        String sub_link;




    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grid);
        mTextView = (TextView) findViewById(R.id.textView);


           search_txt= getIntent().getExtras().getString("search_text");
            String url = "https://www.retailgenius.com/rg_api/get-items-for-search-results.php";

            CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {

                        JSONObject sendResult = new JSONObject((response.toString()));
                        productList =   getListFromJsonObject(sendResult);
                       ListView listview = (ListView) findViewById(R.id.list);
                        ItemListAdapter adapter = new ItemListAdapter(getApplicationContext(), R.layout.activity_view, productList);
                        listview.setAdapter(adapter);

                    //    String viewResult = productList.toString();


                  //    mTextView.setText(response.toString());

                    } catch (Exception e) {
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
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("search_query", search_txt);
                    return params;
                }

            };


            //jsObjRequest.setShouldCache(false);
            MyVolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);

        }
          /*  if(viewmode==0) {
                String[] codeLearnChapters = new String[]{"Android Introduction", "Android Setup/Installation", "Android Hello World", "Android Layouts/Viewgroups", "Android Activity & Lifecycle", "Intents in Android"};
                ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codeLearnChapters);
                ListView codeLearnLessons = (ListView) findViewById(R.id.listView1);
                codeLearnLessons.setAdapter(codeLearnArrayAdapter);

                codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Toast.makeText(getApplicationContext(),
                                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                viewmode=1;
            }
            else if(viewmode==0) {
                String[] codeLearnChapters = new String[]{"Android Introduction", "Android Setup/Installation", "Android Hello World", "Android Layouts/Viewgroups", "Android Activity & Lifecycle", "Intents in Android"};
                ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codeLearnChapters);
                ListView codeLearnLessons = (ListView) findViewById(R.id.listView1);
                codeLearnLessons.setAdapter(codeLearnArrayAdapter);

                codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Toast.makeText(getApplicationContext(),
                                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                viewmode=1;
            } */
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



    public static Object convertJsonItem(Object o) throws JSONException {
        if (o == null) {
            return "null";
        }

        if (o instanceof JSONObject) {
            return getListFromJsonObject((JSONObject) o);
        }


        if (o.equals(Boolean.FALSE) || (o instanceof String &&
                ((String) o).equalsIgnoreCase("false"))) {
            return false;
        }

        if (o.equals(Boolean.TRUE) || (o instanceof String && ((String) o).equalsIgnoreCase("true"))) {
            return true;
        }

        if (o instanceof Number) {
            return o;
        }

        return o.toString();
    }






}


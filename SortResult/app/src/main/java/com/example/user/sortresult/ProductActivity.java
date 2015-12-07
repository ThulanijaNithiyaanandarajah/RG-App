package com.example.user.sortresult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    TextView mTextView;
    String uniqueId;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //mTextView = (TextView) findViewById(R.id.textView4);
        final ImageView img=(ImageView)findViewById(R.id.image);
        final TextView item_name = (TextView)findViewById(R.id.name);
        final TextView item_brand = (TextView)findViewById(R.id.brand);
        TextView item_condition = (TextView)findViewById(R.id.condition);
        final TextView item_availability = (TextView)findViewById(R.id.availability);
        //final TextView item_payment = (TextView)findViewById(R.id.item_payment);
        final TextView item_price = (TextView)findViewById(R.id.price);
        final TextView item_delivery = (TextView)findViewById(R.id.delivery);
        TextView item_cash_on_delivery = (TextView)findViewById(R.id.cod);
        final TextView item_supplier = (TextView)findViewById(R.id.supplier);
        final TextView item_supplier_note = (TextView)findViewById(R.id.note);
        url = "https://www.retailgenius.com/rg_api/get-item-details.php";
        final TextView mtest=(TextView)findViewById(R.id.textView4);

        uniqueId= getIntent().getExtras().getString("unique_id");
     //   mTextView.setText(uniqueId+url);
        CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {


                    JSONObject dataObject = response.getJSONObject("data");
                    item_name.setText(dataObject.getString("item_name"));
                    item_brand.setText(dataObject.getString("item_brand"));
                    item_delivery.setText(dataObject.getString("item_delivery_option"));
                    item_supplier.setText(dataObject.getString("item_supplier"));
                    item_supplier_note.setText(dataObject.getString("item_supplier_note"));
                    item_availability.setText(dataObject.getString("item_availability"));
                    String imageURI = "https://www.retailgenius.com/product_image_lib/thumb/" +uniqueId +"_thumb.jpg";

                    // holder.itemImage.setImageResource(R.drawable.ic_launcher);
                    new DownloadImage(img).execute(imageURI);


                    //    String viewResult = productList.toString();




                    // holder.itemImage.setImageResource(R.drawable.ic_launcher);

                    try {


                    } catch (Exception ex) {

                    }




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
                params.put("unique_id", uniqueId);
                return params;
            }

        };


        //jsObjRequest.setShouldCache(false);
        MyVolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);

       // v.setText(uniqueId);

        //mTextView.setText(uniqueId);


    }

    public static ArrayList<Product> getListFromJsonObject(JSONObject jObject) throws JSONException {
        ArrayList<Product> return_list = new ArrayList<Product>();
        JSONArray dataArray =  jObject.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++)
        {
            String item_name=dataArray.getJSONObject(i).getString("item_name");
            String item_brand = dataArray.getJSONObject(i).getString("item_brand");
            String item_payment_option = dataArray.getJSONObject(i).getString("item_payment_option");
            String item_price = dataArray.getJSONObject(i).getString("item_price");
            String item_delivery_option = dataArray.getJSONObject(i).getString("item_delivery_option");

            return_list.add(new Product(item_name,item_brand,item_payment_option,item_price,item_delivery_option));

        }

        return return_list;


    }



    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {

            this.bmImage = bmImage;

        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap Icon = null;

            try {

                InputStream in = new java.net.URL(urldisplay).openStream();

                Icon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return Icon;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            bmImage.setImageBitmap(result);
        }

    }




    }


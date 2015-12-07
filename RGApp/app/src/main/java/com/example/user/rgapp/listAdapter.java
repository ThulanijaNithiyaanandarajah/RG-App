package com.example.user.rgapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by USER on 12/4/2015.
 */
public class listAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> listItem;
    int Resource;
    LayoutInflater inflater;
    ViewHolder holder;


    public listAdapter(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);

        listItem = objects;
        Resource = resource;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        Product product = listItem.get(position);
        if(convertView == null){
            convertView = inflater.inflate(Resource, null);

            holder = new ViewHolder();

            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.item_name = (TextView)convertView.findViewById(R.id.item_name);
            holder.item_price = (TextView)convertView.findViewById(R.id.item_price);
            holder.item_discount = (TextView)convertView.findViewById(R.id.item_descount);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        String imageURI = "https://www.retailgenius.com/product_image_lib/thumb/" + listItem.get(position).getUnique_id()+"_thumb.jpg";


        new DownloadImage(holder.image).execute(imageURI);
        holder.item_name.setText(listItem.get(position).getItem_name());
        holder.item_price.setText("LKR " + listItem.get(position).getItem_price());
        holder.item_discount.setText(listItem.get(position).getItem_discount());


        return convertView;
    }

    static class ViewHolder {
        public ImageView image;
        public TextView item_name;
        public TextView item_price;
        public TextView item_discount;


    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImage(ImageView bmImage){

            this.bmImage = bmImage;

        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap Icon = null;

            try{

                InputStream in = new java.net.URL(urldisplay).openStream();

                Icon = BitmapFactory.decodeStream(in);
            } catch (Exception e){
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

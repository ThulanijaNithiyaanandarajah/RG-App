package com.example.user.sortresult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2015.
 */
public class ItemListAdapter2 extends ArrayAdapter<Product> {

    ArrayList<Product> listItem;
    int Resource;
    LayoutInflater inflater;
    ViewHolder holder;
    Context context;

    public ItemListAdapter2(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        listItem = objects;
        Resource = resource;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Product product = listItem.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(Resource, null);

            holder = new ViewHolder();

            holder.itemImage = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.brand = (TextView) convertView.findViewById(R.id.brand);
            holder.po = (TextView)convertView.findViewById(R.id.delivery);
            holder.dop= (TextView) convertView.findViewById(R.id.cod);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.layoutId=(LinearLayout)convertView.findViewById(R.id.LinearLayout2);
            //  holder.button = (Button) convertView.findViewById(R.id.button);
            // holder.button.setOnClickListener(new View.OnClickListener() {


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imageURI = "https://www.retailgenius.com/product_image_lib/thumb/" +listItem.get(position).unique_id +"_thumb.jpg";

        // holder.itemImage.setImageResource(R.drawable.ic_launcher);
        new DownloadImage(holder.itemImage).execute(imageURI);
        holder.name.setText(listItem.get(position).getItem_name());
        holder.brand.setText(listItem.get(position).getItem_brand());
        holder.po.setText(listItem.get(position).getItem_payment_option());//for now discount
        holder.dop.setText(listItem.get(position).getItem_delivery_option());
        holder.price.setText(listItem.get(position).getItem_price());
        //    holder.tPrice.setText("LKR " + listItem.get(position).getRegular_price());

        return convertView;
    }

    static class ViewHolder {
        TextView itemTitle;
        TextView itemPrice;
        ImageView itemImage;
        TextView itemOriginalPrice;
        LinearLayout layoutId;
         TextView name;
        TextView brand;
      TextView po;
        TextView dop;
      TextView price;
        //CardView itemLayout;
        //ProgressBar itemImageProgress;
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

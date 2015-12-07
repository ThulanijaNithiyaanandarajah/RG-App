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
public class ItemListAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> listItem;
    int Resource;
    LayoutInflater inflater;
    ViewHolder holder;
    Context context;

    public ItemListAdapter(Context context, int resource, ArrayList<Product> objects) {
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
            //   holder.name = (TextView)convertView.findViewById(R.id.tName);
            holder.itemTitle = (TextView) convertView.findViewById(R.id.title);
            // holder.tBasis = (TextView)convertView.findViewById(R.id.tBasis);
            holder.itemPrice = (TextView) convertView.findViewById(R.id.tPrice);
            holder.itemOriginalPrice = (TextView) convertView.findViewById(R.id.oPrice);
            holder.layoutId=(LinearLayout)convertView.findViewById(R.id.LinearLayout1);
            //  holder.button = (Button) convertView.findViewById(R.id.button);
            // holder.button.setOnClickListener(new View.OnClickListener() {


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imageURI = "https://www.retailgenius.com/product_image_lib/thumb/" +listItem.get(position).unique_id +"_thumb.jpg";

        // holder.itemImage.setImageResource(R.drawable.ic_launcher);
        new DownloadImage(holder.itemImage).execute(imageURI);
        holder.itemTitle.setText(listItem.get(position).getItem_name());
        holder.itemPrice.setText(listItem.get(position).getItem_price());
        holder.itemOriginalPrice.setText(listItem.get(position).getItem_discount());//for now discount
    //    holder.tPrice.setText("LKR " + listItem.get(position).getRegular_price());
       holder.layoutId.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                      // Intent intent=new Intent(parent.getContext(), HotelDetails.class);
                       Intent intent = new Intent(parent.getContext(),ProductActivity.class);

                       intent.putExtra("unique_id",listItem.get(position).unique_id);
                       parent.getContext().startActivity(intent);
                   }
               }

       );
        return convertView;
    }

    static class ViewHolder {
        TextView itemTitle;
        TextView itemPrice;
        ImageView itemImage;
        TextView itemOriginalPrice;
        LinearLayout layoutId;
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

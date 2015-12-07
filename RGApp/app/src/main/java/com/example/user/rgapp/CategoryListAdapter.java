package com.example.user.rgapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 11/30/2015.
 */
public class CategoryListAdapter extends BaseExpandableListAdapter {

    //ImageLoader imageLoader;
    //DisplayImageOptions options;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<SubCategory>> _listDataChild;
    private HashMap<String, String> _listIconHeader;

    public CategoryListAdapter(Context context, List<String> listDataHeader, HashMap<String, String> listIconHeader, HashMap<String, List<SubCategory>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._listIconHeader = listIconHeader;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)) .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final SubCategory subCategory = (SubCategory) getChild(groupPosition, childPosition);

        //final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_child, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.layout_child);

        txtListChild.setText(subCategory.sub_name);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)) .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        String iconSource = (String) getGroupIcon(groupPosition);
        String imageUrl = "http://www.dev.retailgenius.lk"+iconSource;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_parent, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.layout_parent);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        new DownloadImage(imageView).execute(imageUrl);

        /*ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(_context).build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);



        options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();*/



        /*imageLoader.displayImage(imageUrl, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }


        });*/

        /*imageLoader.loadImage(imageUrl, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                imageView.setImageBitmap(loadedImage);

            }
        });*/

        return convertView;
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

    public Object getGroupIcon(int groupPosition) {
        return this._listIconHeader.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
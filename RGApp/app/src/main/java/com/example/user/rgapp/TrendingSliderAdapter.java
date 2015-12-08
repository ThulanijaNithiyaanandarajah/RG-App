package com.example.user.rgapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 12/8/2015.
 */
public class TrendingSliderAdapter extends PagerAdapter{

    private Context ctx;
    private LayoutInflater layoutInflater;
    private List<Product> _listItem;


    public TrendingSliderAdapter(Context ctx, List<Product> listItem){

        this.ctx = ctx;
        this._listItem = listItem;

    }

    @Override
    public int getCount() {

        return this._listItem.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Product product = _listItem.get(position);

        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.image_view);
        TextView iName = (TextView)view.findViewById(R.id.iname);

        iName.setText(product.item_name);

        return view;

    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }


}

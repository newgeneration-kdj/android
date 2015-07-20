package com.android.newgeneration.dandisnap.Profile;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

/**
 * Created by Ãµº¸°æ on 2015-07-20.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds = {R.drawable.view01,R.drawable.view02,R.drawable.view03};
    int rowWidth;

    public ImageAdapter(Context mContext , int rowWidth) {
        this.mContext = mContext;
        this.rowWidth = rowWidth/3;

    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            Log.d("DEBUG", "position : " + position);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(rowWidth, rowWidth));
            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0,0 ,0, 0);
        }else{
            imageView = (ImageView) convertView;
        }

            imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }
}
